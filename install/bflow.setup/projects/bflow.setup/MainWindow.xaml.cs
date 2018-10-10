using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;
using Ionic.Zip;
using IWshRuntimeLibrary;
using System.Threading;
using System.ComponentModel;
using System.Reflection;

namespace bflow.setup {
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        private string _targetPath;
        private string _targetLang;
        private bool _hasInstallStarted = false;
        private bool _hasInstallFinished = false;
        private BackgroundWorker worker;

        public MainWindow() {
            InitializeComponent();
        }

        private void OnHandleBrowseButtonClick(object sender, RoutedEventArgs e) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            DialogResult result = folderBrowserDialog.ShowDialog();
            string selectedPath = folderBrowserDialog.SelectedPath;
            _textboxPath.Text = selectedPath;
            if (_textboxPath.Text != string.Empty) {
                InstallButton.IsEnabled = true;
            }
        }

        private string GetPathFromTextbox() {
            string selectedPath = null;
            Dispatcher.Invoke(() => {
                selectedPath = _textboxPath.Text;
            });
            return selectedPath;
        }

        private void CreateIni() {
            string language = string.Empty;
            string iniText = "-nl ";
            language = _targetLang;
            if (language == "Deutsch") {
                iniText += "de_DE";
            } else if (language == "Englisch") {
                iniText += "en_US";
            } else {
                //What should the application do when there is an error while creating the Ini
                iniText += "Fehler";
            }
            System.IO.File.WriteAllText(@"C:\\Users\\tschiessl\\Documents\\bflow\\bflow.ini", iniText); // Problem
        }

        private void CreateShortcut() {
            string shortcutPath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(shortcutPath);
            string targetPath = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox\\Technikspezialisierung.docx"; // Problem
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            shortcut.IconLocation = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.images.beeDesk.ico").ToString(); // Problem
            //System.Windows.MessageBox.Show(shortcut.IconLocation);
            shortcut.Save();
        }

        private void OnHandleCloseButtonClick(object sender, RoutedEventArgs e) {
            if (_hasInstallStarted == false || _hasInstallFinished == true) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie das Programm schließen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result) {
                    case MessageBoxResult.Yes:
                        Close();
                        break;
                    case MessageBoxResult.No:
                        break;
                }
            } else if (_hasInstallStarted == true && _hasInstallFinished == false) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result) {
                    case MessageBoxResult.Yes:
                        worker.CancelAsync();
                        Dispatcher.Invoke(() => {
                            showProgress.Visibility = Visibility.Hidden;
                        });
                        break;
                    case MessageBoxResult.No:
                        break;
                }
            }
        }

        private void OnHandleExitButtonClick(object sender, System.ComponentModel.CancelEventArgs e) {
            /*MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
            switch (result) {
                case MessageBoxResult.Yes:
                    e.Cancel = false;
                    break;
                case MessageBoxResult.No:
                    e.Cancel = true;
                    break;
            }*/
        }

        private void OnHandleInstallButtonClick(object sender, RoutedEventArgs e) {
            _targetPath = _textboxPath.Text;
            _targetLang = _selectLanguage.Text;
            _hasInstallStarted = true;
            _textboxPath.IsEnabled = false;
            _selectLanguage.IsEnabled = false;
            BrowseButton.IsEnabled = false;
            InstallButton.IsEnabled = false;
            worker = new BackgroundWorker();
            worker.WorkerSupportsCancellation = true;
            worker.RunWorkerCompleted += worker_RunWorkerCompleted;
            worker.WorkerReportsProgress = true;
            worker.DoWork += worker_DoWork;
            worker.ProgressChanged += worker_ProgressChanged;
            worker.RunWorkerAsync(1000);
        }

        private void worker_ProgressChanged(object sender, ProgressChangedEventArgs e) {
            progBar.Value = e.ProgressPercentage;
            progBarText.Text = (string)e.UserState;
        }

        private void worker_DoWork(object sender, DoWorkEventArgs e) {
            var worker = sender as BackgroundWorker;
            string packageName = "Test.zip";// bflow-1.5.0.zip";
            ExtractExistingFileAction fileAction = ExtractExistingFileAction.Throw;
            if (Directory.Exists(_targetPath) && Directory.GetFiles(_targetPath, "*", SearchOption.AllDirectories).Length > 0) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Es existiert bereits ein Verzeichnis! Soll das Verzeichnis überschrieben werden?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result) {
                    case MessageBoxResult.Yes:
                        fileAction = ExtractExistingFileAction.OverwriteSilently;
                        break;
                    case MessageBoxResult.No:
                        System.Windows.MessageBox.Show("Die Installation wird abgebrochen.", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                        return;
                }
            }
            using (Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.zippacks." + packageName)) {
                if (stream == null) throw new InvalidOperationException("Could not open stream to package");

                Dispatcher.Invoke(() => {
                    showProgress.Visibility = Visibility.Visible;
                });

                using (ZipFile zipFile = ZipFile.Read(stream)) {
                    int fileCount = 0;
                    int filesMax = 0;
                    foreach (ZipEntry i in zipFile) {
                        filesMax++;
                    }
                    worker.ReportProgress(0, string.Format("Kopiere 0/{0} Dateien", filesMax));
                    while (fileCount < filesMax) {
                        if (worker.CancellationPending == false) {
                            foreach (ZipEntry j in zipFile) {
                                Thread.Sleep(1000);
                                j.Extract(GetPathFromTextbox(), fileAction);
                                worker.ReportProgress((fileCount + 1) * (100 / filesMax), string.Format("Kopiere {0}/{1} Dateien", fileCount + 1, filesMax));
                                fileCount++;
                            }
                            CreateIni();
                            CreateShortcut();
                            _hasInstallFinished = true;
                        } else {
                            //worker.CancelAsync();
                            return;
                        }
                    }
                }
            }
        }

        private void worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e) {
            if (_hasInstallFinished == true && worker.CancellationPending == false) {
                System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
            }
            //progBar.Value = 0;
            progBarText.Text = "Installation abgeschlossen.";
            //Close();
        }

    }
}
