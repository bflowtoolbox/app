using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;
using Ionic.Zip;
using IWshRuntimeLibrary;
using System.Threading;
using System.ComponentModel;
using System.Reflection;

namespace bflow.setup
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private string _targetPath;
        private string _targetLang;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void GetPathClick(object sender, RoutedEventArgs e)
        {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            DialogResult Result = folderBrowserDialog.ShowDialog();
            string Path = folderBrowserDialog.SelectedPath;
            _textboxPath.Text = Path;
        }

        private string GetPath()
        {
            string Path = null;
            Dispatcher.Invoke(() =>
            {
                Path = _textboxPath.Text;
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
            System.IO.File.WriteAllText(@"C:\\Users\\tschiessl\\Documents\\bflow\\bflow.ini", text); // Problem
        }

        private void CreateShortcut()
        {
            string path = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(path);
            string targetPath = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox\\Technikspezialisierung.docx"; // Problem
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            shortcut.IconLocation = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.images.beeDesk.ico").ToString(); // Problem
            //System.Windows.MessageBox.Show(shortcut.IconLocation);
            shortcut.Save();
        }

        /*private int CountFiles(String src)
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
        }*/

        private void CloseButtonClick(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void DoZipClick(object sender, RoutedEventArgs e)
        {
            _targetPath = _textboxPath.Text;
            _targetLang = language.Text;
            BackgroundWorker worker = new BackgroundWorker();
            worker.RunWorkerCompleted += worker_RunWorkerCompleted;
            worker.WorkerReportsProgress = true;
            worker.DoWork += worker_DoWork;
            worker.ProgressChanged += worker_ProgressChanged;
            worker.RunWorkerAsync(1000);
            string path = this._textboxPath.Text;
        }
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void worker_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progBar.Value = e.ProgressPercentage;
            progText.Text = (string)e.UserState;
        }

        private void worker_DoWork(object sender, DoWorkEventArgs e)
        {
            var worker = sender as BackgroundWorker;
            string packageName = "Test.zip";// bflow-1.5.0.zip";
            ExtractExistingFileAction fileAction = ExtractExistingFileAction.Throw;
            
            if (Directory.Exists(_targetPath) && Directory.GetFiles(_targetPath).Length > 0)
            {
                MessageBoxResult result = System.Windows.MessageBox.Show("Es existiert bereits ein Verzeichnis! Soll das Verzeichnis überschrieben werden?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result)
                {
                    case MessageBoxResult.Yes:
                        fileAction = ExtractExistingFileAction.OverwriteSilently;
                        break;
                    case MessageBoxResult.No:
                        System.Windows.MessageBox.Show("Die Installation wird abgebrochen.", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                        break;
                }
            }
            fileAction = ExtractExistingFileAction.OverwriteSilently; // has to be corrected later on
            using (Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.zippacks." + packageName))
            {
                if (stream == null) throw new InvalidOperationException("Could not open stream to package");

                Dispatcher.Invoke(() =>
                {
                    progress.Visibility = Visibility.Visible; // kein Dispatcher
                });

                int filesMax = 5; //int filesMax = CountFiles(stream.ToString());
                using (ZipFile zipFile = ZipFile.Read(stream))
                {
                    worker.ReportProgress(0, string.Format("Kopiere 0/{0} Dateien", filesMax));
                    int fileCount = 0;
                    while (fileCount < filesMax)
                    {
                        Thread.Sleep(500); //This line has to be removed in the end
                        foreach (ZipEntry a in zipFile)
                        {
                            a.Extract(GetPath(), fileAction);
                            worker.ReportProgress((fileCount + 1) * (100 / filesMax), string.Format("Kopiere {0}/{1} Dateien", fileCount + 1, filesMax));
                            fileCount++;
                        }
                    }
                }
            }
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
