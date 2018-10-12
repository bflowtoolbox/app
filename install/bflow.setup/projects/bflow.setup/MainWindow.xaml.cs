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
        private bool _doOverwrite;
        private bool _hasInstallStarted = false;
        private bool _hasInstallFinished = false;
        private bool _pauseBackgroundworker = false;
        private string _installRoot = "\\bflow Toolbox";
        private string _iniText = "-nl ";
        private BackgroundWorker worker;

        /// This Class is necessary to implement Data Binding
        /// It has to inherit from the class INotifyPropertyChanged to enable Two Way Data Binding
        public class InstallerWindow : INotifyPropertyChanged {
            public event PropertyChangedEventHandler PropertyChanged;

            /// <summary>
            /// The Notify Method tells the particular UI-Element to update its content
            /// and takes the constructor name as argument
            /// </summary>
            /// <param name="argument"></param>
            private void Notify(string argument) {
                if(PropertyChanged != null) {
                    PropertyChanged(this, new PropertyChangedEventArgs(argument));
                }
            }

            /// To bind new data declare a new local variable and add a matching constructor as shown below
            private string targetPath;
            private string targetLang;
            private string progressVisibility;
            private bool textboxPathIsEnabled;
            private bool selectLanguageIsEnabled;
            private bool browseButtonIsEnabled;
            private bool installButtonIsEnabled;

            /// <summary>
            /// Call Notify(ConstructorName) to update the partocular UI element
            /// </summary>
            public string TargetPath {
                get { return targetPath; }
                set {
                    targetPath = value;
                    Notify("TargetPath");
                }
            }

            public string TargetLang {
                get { return targetLang; }
                set {
                    targetLang = value;
                    Notify("TargetLang");
                }
            }

            public string ProgressVisibility {
                get { return progressVisibility; }
                set {
                    progressVisibility = value;
                    Notify("ProgressVisibility");
                }
            }

            public bool TextboxPathIsEnabled {
                get { return textboxPathIsEnabled; }
                set {
                    textboxPathIsEnabled = value;
                    Notify("TextboxPathIsEnabled");
                }
            }

            public bool SelectLanguageIsEnabled {
                get { return selectLanguageIsEnabled; }
                set {
                    selectLanguageIsEnabled = value;
                    Notify("SelectLanguageIsEnabled");
                }
            }

            public bool BrowseButtonIsEnabled {
                get { return browseButtonIsEnabled; }
                set {
                    browseButtonIsEnabled = value;
                    Notify("BrowseButtonIsEnabled");
                }
            }

            public bool InstallButtonIsEnabled {
                get { return installButtonIsEnabled; }
                set {
                    installButtonIsEnabled = value;
                    Notify("InstallButtonIsEnabled");
                }
            }
        }

        InstallerWindow installerWindow = new InstallerWindow();

        public MainWindow() {
            DataContext = installerWindow; // Necessary to enable Data Binding
            installerWindow.TargetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
            installerWindow.TargetLang = "Deutsch";
            installerWindow.ProgressVisibility = "Hidden";
            installerWindow.ProgressVisibility = "Hidden";
            installerWindow.TextboxPathIsEnabled = true;
            installerWindow.SelectLanguageIsEnabled = true;
            installerWindow.BrowseButtonIsEnabled = true;
            installerWindow.InstallButtonIsEnabled = true;
            InitializeComponent();
        }

        /// <summary>
        /// The _textboxPath is  initialised with the Programs folder
        /// When the Browse Button is clicked, a new folder browser dialog is opened
        /// If the folder browser dialog is cancelled, the path falls back to the Programs folder
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnHandleBrowseButtonClick(object sender, RoutedEventArgs e) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            folderBrowserDialog.RootFolder = Environment.SpecialFolder.Desktop;
            DialogResult result = folderBrowserDialog.ShowDialog();
            string selectedPath = folderBrowserDialog.SelectedPath;
            installerWindow.TargetPath = selectedPath;
            if (result == System.Windows.Forms.DialogResult.Cancel) {
                installerWindow.TargetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
            }
        }

        /// <summary>
        /// While the installation is running, the text on the Close Button shows "Abbrechen",
        /// otherwise it shows "Schließen"
        /// </summary>
        private void changeCloseButtonText() {
            if (_hasInstallStarted && !_hasInstallFinished) {
                _closeButton.Content = "Abbrechen";
            } else if (_hasInstallFinished) {
                _closeButton.Content = "Schließen";
            } else {
                _closeButton.Content = "Schließen";
            }
        }

        /// <summary>
        /// Creates a new Ini file in the installation folder with the content of the field _iiniText
        /// </summary>
        private void CreateIni() {
            System.IO.File.WriteAllText(installerWindow.TargetPath + _installRoot + "\\bflow.ini", _iniText);
        }

        /// <summary>
        ///  Creates a shortcut on the desktop with an icon and text
        /// </summary>
        private void CreateShortcut() {
            string shortcutPath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(shortcutPath);
            string targetPath = installerWindow.TargetPath + _installRoot + "\\Technikspezialisierung.docx";// bflow.exe";
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            var iconLocation = installerWindow.TargetPath + _installRoot + "\\beeDesk.ico";
            using (var resource = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.images.beeDesk.ico")) {
                using (var file = new FileStream(iconLocation, FileMode.Create, FileAccess.Write)) {
                    resource.CopyTo(file);
                }
            }
            shortcut.IconLocation = iconLocation;
            shortcut.Save();
        }

        /// <summary>
        /// The Close Button is the Button with the text "Schließen" in the bottom right corner
        /// It has exactly the same functionality as the Exit Button in the upper right corner
        /// the OnHandleExitButtonClick function is called through Close();
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnHandleCloseButtonClick(object sender, RoutedEventArgs e) {
            Close();
        }

        /// <summary>
        /// The Exit Button is the "X" in the upper right corner
        /// The Close Button in the bottom right corner calls this method through Close();
        /// When it's clicked during the installation process, the backgoundworker is paused
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnHandleExitButtonClick(object sender, CancelEventArgs e) {
            if (_hasInstallStarted == false && _hasInstallFinished == false) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie das Programm schließen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result) {
                    case MessageBoxResult.Yes:
                        e.Cancel = false;
                        break;
                    case MessageBoxResult.No:
                        e.Cancel = true;
                        break;
                }
            } else if (_hasInstallStarted && _hasInstallFinished) {
                e.Cancel = false;
            }
            else if (_hasInstallStarted && _hasInstallFinished == false) {
                e.Cancel = true;
                _pauseBackgroundworker = true;
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                _pauseBackgroundworker = false;
                switch (result) {
                    case MessageBoxResult.Yes:
                        worker.CancelAsync();
                        installerWindow.ProgressVisibility = "Hidden";
                        _hasInstallStarted = false;
                        changeCloseButtonText();
                        break;
                    case MessageBoxResult.No:
                        return;
                }
            }
        }

        /// <summary>
        /// Enables or disabled the UI for user input
        /// </summary>
        /// <param name="enable"></param>
        private void EnableUI(bool enable) {
            installerWindow.TextboxPathIsEnabled = enable;
            installerWindow.SelectLanguageIsEnabled = enable;
            installerWindow.BrowseButtonIsEnabled = enable;
            installerWindow.InstallButtonIsEnabled = enable;
        }

        /// At first the language is validated and the program checks if already existing data should be overwritten
        /// then the backgroundworker is started
        private void OnHandleInstallButtonClick(object sender, RoutedEventArgs e) {
            if (installerWindow.TargetLang == "Deutsch") {
                _iniText += "de_DE";
            } else if (installerWindow.TargetLang == "Englisch") {
                _iniText += "en_US";
            } else {
                System.Windows.MessageBox.Show("Bitte geben Sie eine gültige Sprache ein!");
                return;
            }
            _hasInstallStarted = true;
            EnableUI(false);
            _doOverwrite = doOverwrite(installerWindow.TargetPath + _installRoot);
            changeCloseButtonText();
            worker = new BackgroundWorker();
            worker.WorkerSupportsCancellation = true;
            worker.RunWorkerCompleted += worker_RunWorkerCompleted;
            worker.WorkerReportsProgress = true;
            worker.DoWork += worker_DoWork;
            worker.ProgressChanged += worker_ProgressChanged;
            worker.RunWorkerAsync(1000);
        }

        /// <summary>
        /// The ProgressChanged event is responsible for the progressbar and the progresstext
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void worker_ProgressChanged(object sender, ProgressChangedEventArgs e) {
            progBar.Value = e.ProgressPercentage;
            progBarText.Text = (string)e.UserState;
        }

        /// <summary>
        /// Checks if the selected directory does already exists and asks the user if it should overwrite
        /// </summary>
        /// <param name="directory"></param>
        /// <returns></returns>
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

        /// <summary>
        /// The DoWork event of the backgroundworker is responsible for the actual process of
        /// extracting the data to the install directory
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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
                installerWindow.ProgressVisibility = "Visible";
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
                                Thread.Sleep(300); //has to be deleted later on
                                j.Extract(installerWindow.TargetPath, fileAction);
                                worker.ReportProgress((fileCount + 1) * (100 / filesMax), string.Format("Kopiere {0}/{1} Dateien", fileCount + 1, filesMax));
                                fileCount++;
                            } else {
                                _hasInstallStarted = false;
                                EnableUI(true);
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

        /// <summary>
        /// This event is fired when the backgroundworker has actually completed its tasks
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e) {
            if (_hasInstallFinished) {
                System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                changeCloseButtonText();
            }
            progBarText.Text = "Installation abgeschlossen.";
        }

    }
}
