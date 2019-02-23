package org.bflow.toolbox.hive.mbe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Provides methods to check if the user has a valid licence.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-23
 *
 */
public class LicenceManager {
	private final Log _log = LogFactory.getLog(LicenceManager.class);
	
	/**
	 * Runs the licence validation. If the current licence is not valid, the
	 * workbench is shut down asynchronously. If this is the first start of
	 * workbench, a demo licence which expires after 30 days is installed.
	 */
	public void check() {
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		Path path = Paths.get(workspacePath, "/.config/.lock");
		
		byte[] key = getNiAddress();
		if (key == null) {
			_log.error("Key is NULL");
		}
		
		Licence licence;
		if (Files.exists(path)) {
			// Read existing licence
			licence = readLicenceValue(path, key);
		} else {
			// Create demo licence
			licence = createDemoLicence();
			writeLicenceValue(licence, path, key);
		}
		
		// Validate licence
		boolean validLicence = validateLicence(licence);
		if (validLicence) return;
		
		// Enqueue the shutdown to finalize the start-up process			
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				// TODO Display dialog
				PlatformUI.getWorkbench().close();
			}				
		});
	}
	
	/**
	 * Returns TRUE if the given {@code licence} is valid. A licence 
	 * is valid, when its begin is after today and expires is before 
	 * today. If {@code licence} is NULL, the method also returns FALSE.
	 */
	private boolean validateLicence(Licence licence) {
		if (licence == null) return false;
		
		Date begin = licence.begin;
		Date expires = licence.expires;
		
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		// Begin must be in the past
		if (today.before(begin)) return false;
		
		// Expires must be in the future
		if (today.after(expires)) return false;
		
		return true;
	}
	
	/**
	 * Encrypts the given {@code licence} with the given {@code key} and writes 
	 * it in a file with the specified {@code path}. If {@code key} is NULL or 
	 * empty, nothing happens.
	 */
	private void writeLicenceValue(Licence licence, Path path, byte[] key) {
		if (key == null || key.length == 0) return;
		
		String licenceText = licence.toString();
		byte[] textData = licenceText.getBytes();
		byte[] encData = new byte[textData.length * 2];
		int p = 0;
		for (int i = -1, j = 0; ++i != textData.length;) {
			byte b = textData[i];
			
			if (j == key.length)
				j = 0;
			
			byte s = key[j++]; 
			
			int x = b + s;
			byte flag = 0;
			if (x > Byte.MAX_VALUE) {
				flag = 1;
				x -= Byte.MAX_VALUE;
			}
			
			if (x < Byte.MIN_VALUE) {
				flag = -1;
				x -= Byte.MIN_VALUE;
			}
			
			byte bx = (byte) x;
			encData[p++] = flag;
			encData[p++] = bx;
		}
		
		try {
			Files.write(path, encData);
		} catch (IOException ex) {
			_log.error("Error on writing licence file", ex);
		}
	}
	
	/**
	 * Reads the licence from a file located at the specified {@code path} 
	 * and decrypts it using the given {@code key}. If the key is NULL 
	 * or empty, this method returns NULL.
	 */
	private Licence readLicenceValue(Path path, byte[] key) {
		if (key == null || key.length == 0) return null;
		
		byte[] encData;
		try {
			encData = Files.readAllBytes(path);
		} catch (IOException ex) {
			_log.error("Error on reading licence file", ex);
			return null;
		}
		
		byte[] licenceData = new byte[encData.length / 2];
		int p = 0;
		for (int i = -1, j = 0; (i+1) != encData.length;) {
			byte flag = encData[++i];
			int bx = encData[++i];
			
			if (j == key.length)
				j = 0;
			
			byte s = key[j++];
			if (flag == 1) {
				bx += Byte.MAX_VALUE;
			} 
			
			if (flag == -1) {
				bx += Byte.MIN_VALUE;
			}
			
			int x = bx - s;
			byte b = (byte) x;
			licenceData[p++] = b;
		}
		
		String licenceText = new String(licenceData, 0, p);
		return Licence.parse(licenceText);
	}
	
	/** Creates a demo licence. */
	private Licence createDemoLicence() {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.DATE, 30);
		Date expires = cal.getTime();
		return new Licence(today, expires);
	}
	
	/**
	 * Returns the hardware address of the network interface that maps localhost.
	 * Note, the return value may be NULL or empty.
	 */
	private byte[] getNiAddress() {
		try {
			NetworkInterface ni2 = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
			return ni2.getHardwareAddress();
		} catch (SocketException | UnknownHostException ex) {
			_log.error("Error on querying network interface", ex);
			return null;
		}		
	}
	
	/**
	 * Describes a licence.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 2019-02-23
	 *
	 */
	static class Licence {
		public Date begin;
		public Date expires;
		
		/** Initializes the new instance with the given arguments. */
		public Licence(Date bgn, Date exp) {
			begin = bgn;
			expires = exp;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			String beginText = sdf.format(begin);
			String exprsText = sdf.format(expires);
			return beginText.concat("-").concat(exprsText);
		}
		
		/**
		 * Returns a new instance by parsing the given {@code value}. 
		 * If the {@code value} is invalid, NULL is returned.
		 */
		public static Licence parse(String value) {
			if (value == null || value.length() == 0) return null;
			if (value.charAt(10) != '-') return null;
			
			String beginText = value.substring(0, 10);
			String exprsText = value.substring(11);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Date bgn;
			Date exp;
			try {
				 bgn = sdf.parse(beginText);
				 exp = sdf.parse(exprsText);
			} catch(ParseException ex) {
				return null;
			}
			
			if (bgn == null || exp == null) return null;
			return new Licence(bgn, exp);
		}
	}
	
	private void platformSpecific() throws Exception {
		// Appproach 1:
		NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLoopbackAddress());
		byte[] ba = ni.getHardwareAddress();
		NetworkInterface ni2 = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		byte[] ba2 = ni2.getHardwareAddress();
		
		// Approach 2:
		// See https://stackoverflow.com/questions/49488624/how-to-get-a-computer-specific-id-number-using-java
		Process proc = Runtime.getRuntime().exec("wmic csproduct get UUID");
		proc.waitFor();
		StringBuilder sb = new StringBuilder(100);
		try (InputStream inputStream = proc.getInputStream()) {
			try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
				try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
					String line;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}					
				}
			}			
		}
		
		String procOut = sb.toString();
	}
}
