using System;
using System.IO;

namespace bflow.setup {
    /// <summary> Provides access to common win32 operations. </summary>
    public static class Win32 {
        /// <summary>
        /// Copies the <paramref name="srcDir"/> to the specified <paramref name="dest"/>
        /// including all included files and sub-folders if desired.
        /// </summary>
        /// <param name="srcDir">Source directory to copy</param>
        /// <param name="dest">Target destination</param>
        /// <param name="copySubDirs">Set TRUE to copy sub directories</param>
        public static void CopyDirectory(string srcDir, string dest, bool copySubDirs) {
            if (string.IsNullOrEmpty(srcDir)) throw new ArgumentNullException(nameof(srcDir));
            if (string.IsNullOrEmpty(dest)) throw new ArgumentNullException(nameof(dest));

            // Get the subdirectories for the specified directory.
            DirectoryInfo dir = new DirectoryInfo(srcDir);

            if (!dir.Exists) throw new DirectoryNotFoundException("Das angegebene Quellverzeichnis konnte nicht gefunden werden: " + srcDir);

            DirectoryInfo[] dirs = dir.GetDirectories();

            // If the destination directory doesn't exist, create it.
            if (!Directory.Exists(dest)) {
                Directory.CreateDirectory(dest);
            }

            // Get the files in the directory and copy them to the new location.
            FileInfo[] files = dir.GetFiles();
            foreach (FileInfo file in files) {
                string temppath = Path.Combine(dest, file.Name);
                file.CopyTo(temppath, false);
            }

            if (!copySubDirs) return;

            // If copying subdirectories, copy them and their contents to new location.
            foreach (DirectoryInfo subdir in dirs) {
                string temppath = Path.Combine(dest, subdir.Name);
                CopyDirectory(subdir.FullName, temppath, true);
            }
        }
    }
}