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
        private bool _doOverwrite;
        private bool _hasInstallStarted = false;
        private bool _hasInstallFinished = false;
        private bool _pauseBackgroundworker = false;
        private BackgroundWorker worker;
        
        public MainWindow() {
            InitializeComponent();
        }

        private void OnHandleBrowseButtonClick(object sender, RoutedEventArgs e) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            folderBrowserDialog.RootFolder = Environment.SpecialFolder.Desktop;
            DialogResult result = folderBrowserDialog.ShowDialog();
            string selectedPath = folderBrowserDialog.SelectedPath;
            _textboxPath.Text = selectedPath;
            if (result == System.Windows.Forms.DialogResult.Cancel) {
                _textboxPath.Text = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow"; //später ändern
            }
        }

        private void changeCloseButtonText() {
            if (_hasInstallStarted) {
                _closeButton.Content = "Abbrechen";
            } else {
                _closeButton.Content = "Schließen";
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
            using (var resource = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.images.beeDesk.ico")) {
                using (var file = new FileStream("C:\\Users\\tschiessl\\Documents\\bflow\\bflow\\beeDesk.ico", FileMode.Create, FileAccess.Write)) {
                    resource.CopyTo(file);
                }
            }
            shortcut.IconLocation = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow\\beeDesk.ico"; // Problem
            shortcut.Save();
        }

        private void OnHandleCloseButtonClick(object sender, RoutedEventArgs e) {
            if (_hasInstallStarted == false || _hasInstallFinished == true) {
                Close();
            } else if (_hasInstallStarted == true && _hasInstallFinished == false) {
                _pauseBackgroundworker = true;
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                _pauseBackgroundworker = false;
                switch (result) {
                    case MessageBoxResult.Yes:
                        worker.CancelAsync();
                        Dispatcher.Invoke(() => {
                            showProgress.Visibility = Visibility.Hidden;
                        });
                        _hasInstallStarted = false;
                        changeCloseButtonText();
                        break;
                    case MessageBoxResult.No:
                        return;
                }
            }
        }

        private void OnHandleExitButtonClick(object sender, System.ComponentModel.CancelEventArgs e) {
            if (_hasInstallStarted == false || _hasInstallFinished == true) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie das Programm schließen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result) {
                    case MessageBoxResult.Yes:
                        e.Cancel = false;
                        break;
                    case MessageBoxResult.No:
                        e.Cancel = true;
                        break;
                }
            } else if (_hasInstallStarted == true && _hasInstallFinished == false) {
                e.Cancel = true;
                _pauseBackgroundworker = true;
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                _pauseBackgroundworker = false;
                switch (result) {
                    case MessageBoxResult.Yes:
                        worker.CancelAsync();
                        Dispatcher.Invoke(() => {
                            showProgress.Visibility = Visibility.Hidden;
                        });
                        _hasInstallStarted = false;
                        changeCloseButtonText();
                        break;
                    case MessageBoxResult.No:
                        return;
                }
            }
        }

        private void EnableUI(bool enable) {
            _textboxPath.IsEnabled = enable;
            _selectLanguage.IsEnabled = enable;
            BrowseButton.IsEnabled = enable;
            InstallButton.IsEnabled = enable;
        }

        private void OnHandleInstallButtonClick(object sender, RoutedEventArgs e) {
            _targetPath = _textboxPath.Text;
            _targetLang = _selectLanguage.Text;
            _hasInstallStarted = true;
            EnableUI(false);
            _doOverwrite = doOverwrite(_targetPath);
            changeCloseButtonText();
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

        private bool doOverwrite(string directory) {
            if (Directory.Exists(directory) && Directory.GetFiles(directory, "*", SearchOption.AllDirectories).Length > 0) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Es existiert bereits ein Verzeichnis! Soll das Verzeichnis überschrieben werden?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                if (result == MessageBoxResult.No) {
                    System.Windows.MessageBox.Show("Die Installation wird abgebrochen.", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                    EnableUI(true);
                    _hasInstallStarted = false;
                    return false;
                }
            }
            return true;
        }

        private void worker_DoWork(object sender, DoWorkEventArgs e) {
            var worker = sender as BackgroundWorker;
            string packageName = "Test.zip";// bflow-1.5.0.zip";
            ExtractExistingFileAction fileAction = ExtractExistingFileAction.Throw;
            if (_doOverwrite) {
                fileAction = ExtractExistingFileAction.OverwriteSilently;
            } else {
                return;
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
                        foreach (ZipEntry j in zipFile) {
                            while (_pauseBackgroundworker) {
                                Thread.Sleep(500);
                            }
                            if (worker.CancellationPending == false) {
                                Thread.Sleep(200); //has to be deleted later on
                                j.Extract(GetPathFromTextbox(), fileAction);
                                worker.ReportProgress((fileCount + 1) * (100 / filesMax), string.Format("Kopiere {0}/{1} Dateien", fileCount + 1, filesMax));
                                fileCount++;
                            } else {
                                _hasInstallStarted = false;
                                Dispatcher.Invoke(() => {
                                    EnableUI(true);
                                });
                                return;
                            }
                        }
                    }
                    CreateIni();
                    CreateShortcut();
                    _hasInstallFinished = true;
                }
            }
        }

        private void worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e) {
            if (_hasInstallFinished) {
                System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                _hasInstallStarted = false;
                changeCloseButtonText();
            }
            progBarText.Text = "Installation abgeschlossen.";
        }

    }
}
