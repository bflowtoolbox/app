package util;

public class Execute
{
    public static int command(String line) {
	System.out.println(line);
	try {
       	Process proc = Runtime.getRuntime().exec(line);
	int exit_val = proc.waitFor();
	return exit_val;
	} catch (Exception e) {
		e.printStackTrace();
		return -1;
	}
    }

    public static int command(String line, boolean wait) {
	System.out.println(line);
	try {
       	Process proc = Runtime.getRuntime().exec(line);
	int exit_val;
	if (wait) {
		exit_val = proc.waitFor();
		return exit_val;
	} else {
		return 0;
	}
	} catch (Exception e) {
		e.printStackTrace();
		return -1;
	}
    }
}
