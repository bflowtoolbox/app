using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;
using Ionic.Zip;
using IWshRuntimeLibrary;
using System.Threading;
using System.ComponentModel;

namespace bflow.setup
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }
         
        private void GetPathClick(object sender, RoutedEventArgs e)
        {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            DialogResult Result = folderBrowserDialog.ShowDialog();
            string Path = folderBrowserDialog.SelectedPath;
            path.Text = Path;
        }

        private string GetPath()
        {
            string Path = "";
            Dispatcher.Invoke(() =>
            {
                Path = path.Text;
            });
            return Path;
        }

        private void CreateIni()
        {
            string lang = "";
            string text = "-nl ";
            Dispatcher.Invoke(() =>
            {
                lang = language.Text;
            });
            if (lang == "Deutsch")
            {
                text += "de-DE";
            }
            else if (lang == "Englisch")
            {
                text += "en-US";
            }
            else
            {
                //What should the application do when there is an error while creating the Ini
                text += "Fehler";
            }
            System.IO.File.WriteAllText(@"C:\\Users\\tschiessl\\Documents\\bflow\\bflow.ini", text);
        }

        private void CreateShortcut()
        {
            string path = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(path);
            string targetPath = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox\\Technikspezialisierung.docx";
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            shortcut.IconLocation = "C:\\Users\\tschiessl\\Documents\\Quellcode\\bflow\\install\\bflow.setup\\img\\bee.ico";
            shortcut.Save();
        }

        private int CountFiles(String src)
        {
            int files = 0;
            using (ZipFile zipFile = ZipFile.Read(src))
            {
                foreach (ZipEntry i in zipFile)
                {
                    files++;
                }
            }
            return files;
        }

        private void CloseButtonClick(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void DoZipClick(object sender, RoutedEventArgs e)
        {
            BackgroundWorker worker = new BackgroundWorker();
            worker.RunWorkerCompleted += worker_RunWorkerCompleted;
            worker.WorkerReportsProgress = true;
            worker.DoWork += worker_DoWork;
            worker.ProgressChanged += worker_ProgressChanged;
            worker.RunWorkerAsync(10000);
            string path = this.path.Text;
        }

        private void worker_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progBar.Value = e.ProgressPercentage;
            progText.Text = (string)e.UserState;
        }

        private void worker_DoWork(object sender, DoWorkEventArgs e)
        {
            var worker = sender as BackgroundWorker;
            string src = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox.zip";
            string dest = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox";
            int filesMax = CountFiles(src);
            using (ZipFile zipFile = ZipFile.Read(src))
            {
                if (Directory.Exists(dest) && Directory.GetFiles(dest).Length > 0)
                {
                    MessageBoxResult result = System.Windows.MessageBox.Show("Es existiert bereits ein Verzeichnis! Soll das Verzeichnis überschrieben werden?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                    switch (result)
                    {
                        case MessageBoxResult.Yes:
                            Dispatcher.Invoke(() =>
                            {
                                progress.Visibility = Visibility.Visible;
                            });
                            foreach (ZipEntry i in zipFile)
                            {
                                i.Extract(GetPath(), ExtractExistingFileAction.OverwriteSilently);
                            }
                            CreateIni();
                            CreateShortcut();
                            break;
                        case MessageBoxResult.No:
                            System.Windows.MessageBox.Show("Die Installation wird abgebrochen.", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                            break;
                    }
                }
                else
                {
                    Dispatcher.Invoke(() =>
                    {
                        progress.Visibility = Visibility.Visible;
                    });
                    foreach (ZipEntry i in zipFile)
                    {
                        i.Extract(GetPath());
                    }
                    CreateIni();
                    CreateShortcut();
                }
            }
            worker.ReportProgress(0, string.Format("Kopiere 0/{0} Dateien", filesMax));
            for (int i = 0; i < filesMax; i++)
            {
                Thread.Sleep(500); //This line has to be removed in the end
                worker.ReportProgress((i + 1) * (100/filesMax), sString.Format("Kopiere {0}/{1} Dateien", i + 1, filesMax));
            }
            worker.ReportProgress(100, "Installation abgeschlossen."); 
        }

        private void worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
            progBar.Value = 0;
            progText.Text = "";
            Close();
        }

    }
}
