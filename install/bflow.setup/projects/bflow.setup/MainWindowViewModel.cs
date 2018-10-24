using Ionic.Zip;
using IWshRuntimeLibrary;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Reflection;
using System.Threading;
using System.Windows;
using System.Windows.Forms;
using System.Windows.Input;

namespace bflow.setup {
    public class MainWindowViewModel : INotifyPropertyChanged {
        private string _targetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
        private string _groupTargetPath = string.Empty;
        private string _language = "Deutsch";
        private string _closeButtonText = "Schließen";
        private string _progressBarText = string.Empty;
        private int _progressBarValue;
        private bool _textboxPathIsEnabled = true;
        private bool _textboxGroupPathIsEnabled = false;
        private Visibility _progressVisibility { get; set; } = Visibility.Hidden;
        private bool _selectLanguageIsEnabled = true;
        private bool _browseButtonIsEnabled = true;
        private bool _browseGroupButtonIsEnabled = false;
        private bool _installButtonIsEnabled = true;
        private bool _checkboxGroupPathIsEnabled = true;
        private bool _checkboxGroupPathIsChecked = false;
        public Action CloseAction { get; set; }
        Dictionary<string, string> _iniTextA = new Dictionary<string, string>();

        private const string BflowPackageName = "bflow-1.5.0.zip";
        private bool _doOverwrite;
        private bool _hasInstallStarted = false;
        private bool _hasInstallFinished = false;
        private bool _pauseBackgroundworker = false;
        private string _installRoot = "\\bflow Toolbox";
        private string _iniText = "-nl ";
        private BackgroundWorker worker;

        public ICommand CloseCommand { get; set; } = new RelayCommand(OnExecuteClose, OnCanExecuteClose);
        public ICommand ExitCommand { get; set; }
        public ICommand BrowseCommand { get; set; }
        public ICommand BrowseGroupCommand { get; set; }
        public ICommand InstallCommand { get; set; }
        public ICommand CheckGroupCommand { get; set; }

        public MainWindowViewModel() {
            BrowseCommand = new RelayCommand(OnExecuteBrowse, OnCanExecuteBrowse);
            BrowseGroupCommand = new RelayCommand(OnExecuteBrowseGroup, OnCanExecuteBrowseGroup);
            ExitCommand = new RelayCommand(OnExecuteExit, OnCanExecuteExit);
            InstallCommand = new RelayCommand(OnExecuteInstall, OnCanExecuteInstall);
            CheckGroupCommand = new RelayCommand(OnExecuteCheckGroup, OnCanExecuteCheckGroup);
        }

        public string TargetPath {
            get {
                return _targetPath;
            }

            set {
                if (_targetPath != value) {
                    _targetPath = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(TargetPath)));
                }
            }
        }

        public string GroupTargetPath {
            get {
                return _groupTargetPath;
            }

            set {
                if (_groupTargetPath != value) {
                    _groupTargetPath = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(GroupTargetPath)));
                }
            }
        }

        public string Language {
            get { return _language; }

            set {
                if (_language != value) {
                    _language = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(Language)));
                }
            }
        }

        public string CloseButtonText {
            get { return _closeButtonText; }

            set {
                if (_closeButtonText != value) {
                    _closeButtonText = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(CloseButtonText)));
                }
            }
        }

        public string ProgressBarText {
            get { return _progressBarText; }

            set {
                if (_progressBarText != value) {
                    _progressBarText = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(ProgressBarText)));
                }
            }
        }

        public int ProgressBarValue {
            get { return _progressBarValue; }

            set {
                if (_progressBarValue != value) {
                    _progressBarValue = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(ProgressBarValue)));
                }
            }
        }

        public Visibility ProgressVisibility {
            get { return _progressVisibility; }

            set {
                if (_progressVisibility != value) {
                    _progressVisibility = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(ProgressVisibility)));
                }
            }
        }

        public bool TextboxPathIsEnabled {
            get { return _textboxPathIsEnabled; }

            set {
                if (_textboxPathIsEnabled != value) {
                    _textboxPathIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(TextboxPathIsEnabled)));
                }
            }
        }

        public bool TextboxGroupPathIsEnabled {
            get { return _textboxGroupPathIsEnabled; }

            set {
                if (_textboxGroupPathIsEnabled != value) {
                    _textboxGroupPathIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(TextboxGroupPathIsEnabled)));
                }
            }
        }

        public bool SelectLanguageIsEnabled {
            get { return _selectLanguageIsEnabled; }

            set {
                if (_selectLanguageIsEnabled != value) {
                    _selectLanguageIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(SelectLanguageIsEnabled)));
                }
            }
        }

        public bool BrowseButtonIsEnabled {
            get { return _browseButtonIsEnabled; }

            set {
                if (_browseButtonIsEnabled != value) {
                    _browseButtonIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(BrowseButtonIsEnabled)));
                }
            }
        }

        public bool BrowseGroupButtonIsEnabled {
            get { return _browseGroupButtonIsEnabled; }

            set {
                if (_browseGroupButtonIsEnabled != value) {
                    _browseGroupButtonIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(BrowseGroupButtonIsEnabled)));
                }
            }
        }

        public bool InstallButtonIsEnabled {
            get { return _installButtonIsEnabled; }

            set {
                if (_installButtonIsEnabled != value) {
                    _installButtonIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(InstallButtonIsEnabled)));
                }
            }
        }

        public bool CheckboxGroupPathIsEnabled {
            get { return _checkboxGroupPathIsEnabled; }

            set {
                if (_checkboxGroupPathIsEnabled != value) {
                    _checkboxGroupPathIsEnabled = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(CheckboxGroupPathIsEnabled)));
                }
            }
        }

        public bool CheckboxGroupPathIsChecked {
            get { return _checkboxGroupPathIsChecked; }

            set {
                if (_checkboxGroupPathIsChecked != value) {
                    _checkboxGroupPathIsChecked = value;
                    PropertyChanged(this, new PropertyChangedEventArgs(nameof(CheckboxGroupPathIsChecked)));
                }
            }
        }

        private bool OnCanExecuteBrowse(object arg) {
            return true;
        }

        /// <summary>
        /// The _textboxPath is  initialised with the Programs folder
        /// When the Browse Button is clicked, a new folder browser dialog is opened
        /// If the folder browser dialog is cancelled, the path falls back to the Programs folder
        /// </summary>
        private void OnExecuteBrowse(object obj) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            folderBrowserDialog.RootFolder = Environment.SpecialFolder.Desktop;
            DialogResult result = folderBrowserDialog.ShowDialog();
            string selectedPath = folderBrowserDialog.SelectedPath;
            TargetPath = selectedPath;
            if (result == DialogResult.Cancel) {
                TargetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
            }
        }

        /// <summary>
        /// The _textboxGroupPath is empty by default
        /// When the Browse Group Button is clicked, a new folder browser dialog is opened
        /// If the folder browser dialog is cancelled, the path is empty again
        /// </summary>
        private void OnExecuteBrowseGroup(object obj) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            folderBrowserDialog.RootFolder = Environment.SpecialFolder.Desktop;
            DialogResult result = folderBrowserDialog.ShowDialog();
            string selectedPath = folderBrowserDialog.SelectedPath;
            GroupTargetPath = selectedPath;
            if (GroupTargetPath != String.Empty) {
                InstallButtonIsEnabled = true;
            } else {
                InstallButtonIsEnabled = false;
            }
            if (result == DialogResult.Cancel) {
                GroupTargetPath = String.Empty;
            }
        }

        private bool OnCanExecuteBrowseGroup(object arg) {
            return true;
        }

        private static bool OnCanExecuteClose(object arg) {
            return true;
        }

        private static void OnExecuteClose(object obj) {
            App.Current.MainWindow.Close();
        }

        private bool OnCanExecuteExit(object arg) {
            return true;
        }

        /// <summary>
        /// The Exit Button is the "X" in the upper right corner
        /// The Close Button in the bottom right corner calls this method through Close();
        /// When it's clicked during the installation process, the backgoundworker is paused
        /// </summary>
        private void OnExecuteExit(object obj) {
            CancelEventArgs eventArgs = (CancelEventArgs)obj;
            if (_hasInstallStarted == false && _hasInstallFinished == false) {
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie das Programm schließen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                switch (result) {
                    case MessageBoxResult.Yes:
                        eventArgs.Cancel = false;
                        break;
                    case MessageBoxResult.No:
                        eventArgs.Cancel = true;
                        break;
                }
            } else if (_hasInstallStarted && _hasInstallFinished) {
                eventArgs.Cancel = false;
            } else if (_hasInstallStarted && _hasInstallFinished == false) {
                eventArgs.Cancel = true;
                _pauseBackgroundworker = true;
                MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                _pauseBackgroundworker = false;
                switch (result) {
                    case MessageBoxResult.Yes:
                        worker.CancelAsync();
                        ProgressVisibility = Visibility.Hidden;
                        _hasInstallStarted = false;
                        changeCloseButtonText();
                        break;
                    case MessageBoxResult.No:
                        return;
                }
            }
        }

        /// <summary>
        /// While the installation is running, the text on the Close Button shows "Abbrechen",
        /// otherwise it shows "Schließen"
        /// </summary>
        private void changeCloseButtonText() {
            if (_hasInstallStarted && !_hasInstallFinished) {
                CloseButtonText = "Abbrechen";
            } else if (_hasInstallFinished) {
                CloseButtonText = "Schließen";
            } else {
                CloseButtonText = "Schließen";
            }
        }

        /// <summary>
        /// Creates a new Ini file in the installation folder with the content of the field _iiniText
        /// </summary>
        private void CreateIni() {
            string line = string.Empty;
            int lineCount = 0;
            try {
                StreamReader streamCounter = new StreamReader(TargetPath + _installRoot + "\\bflow.ini");
                while (streamCounter.ReadLine() != null) {
                    lineCount++;
                }
                streamCounter.Close();
                StreamReader streamReader = new StreamReader(TargetPath + _installRoot + "\\bflow.ini");
                int arrLength = lineCount / 2;
                string[] iniKey = new string[arrLength];
                string[] iniValue = new string[arrLength];
                while (line != null) {
                    //Console.WriteLine(text);
                    for (int i = 0; i < arrLength; i++) {
                        line = streamReader.ReadLine();
                        if (line.Substring(0, 1) == "-") {
                            iniKey[i] = line;
                        } else if (line.Substring(0, 1) != "-") {
                            iniValue[i] = line;
                        } else {
                            //Exception
                        }
                        //_iniTextA.Add(iniKey, iniValue);
                    }
                }
                streamReader.Close();
                //System.IO.File.WriteAllText(TargetPath + _installRoot + "\\bflow.ini", _iniText);
            } catch (Exception e) {
                //Console.WriteLine("Exception: " + e.Message);
            } finally {
                //Console.WriteLine("Executing finally block.");
            }
        }

        /// <summary>
        ///  Creates a shortcut on the desktop with an icon and text
        /// </summary>
        private void CreateShortcut() {
            string shortcutPath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(shortcutPath);
            string targetPath = TargetPath + _installRoot + "\\Technikspezialisierung.docx";// bflow.exe";
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            var iconLocation = TargetPath + _installRoot + "\\beeDesk.ico"; // global??
            using (var resource = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.images.beeDesk.ico")) {
                using (var file = new FileStream(iconLocation, FileMode.Create, FileAccess.Write)) {
                    resource.CopyTo(file);
                }
            }
            shortcut.IconLocation = iconLocation;
            shortcut.Save();
        }

        /// <summary>
        /// Enables or disabled the UI for user input
        /// </summary>
        /// <param name="enable"></param>
        private void EnableUI(bool enable) {
            TextboxPathIsEnabled = enable;
            SelectLanguageIsEnabled = enable;
            BrowseButtonIsEnabled = enable;
            BrowseButtonIsEnabled = enable;
            InstallButtonIsEnabled = enable;
            CheckboxGroupPathIsEnabled = enable;
            if (CheckboxGroupPathIsChecked) {
                TextboxGroupPathIsEnabled = enable;
                BrowseGroupButtonIsEnabled = enable;
            } else {
                TextboxGroupPathIsEnabled = false;
                BrowseGroupButtonIsEnabled = false;
            }

        }

        private bool OnCanExecuteInstall(object arg) {
            if (Language == "Deutsch") {
                _iniText += "de_DE";
                return true;
            } else if (Language == "Englisch") {
                _iniText += "en_US";
                return true;
            } else {
                System.Windows.MessageBox.Show("Bitte geben Sie eine gültige Sprache ein!");
                return false;
            }
        }

        private void OnExecuteInstall(object obj) {
            _hasInstallStarted = true;
            EnableUI(false);
            _doOverwrite = doOverwrite(TargetPath + _installRoot);
            changeCloseButtonText();
            worker = new BackgroundWorker();
            worker.WorkerSupportsCancellation = true;
            worker.RunWorkerCompleted += worker_RunWorkerCompleted;
            worker.WorkerReportsProgress = true;
            worker.DoWork += worker_DoWork;
            worker.ProgressChanged += worker_ProgressChanged;
            worker.RunWorkerAsync(1000);
        }

        private void OnExecuteCheckGroup(object obj) {
            if (CheckboxGroupPathIsChecked) {
                TextboxGroupPathIsEnabled = true;
                BrowseGroupButtonIsEnabled = true;
                if (GroupTargetPath == String.Empty) {
                    InstallButtonIsEnabled = false;
                }
            } else {
                TextboxGroupPathIsEnabled = false;
                BrowseGroupButtonIsEnabled = false;
                InstallButtonIsEnabled = true;
            }
        }

        private bool OnCanExecuteCheckGroup(object arg) {
            return true;
        }

        /// <summary>
        /// The ProgressChanged event is responsible for the progressbar and the progresstext
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void worker_ProgressChanged(object sender, ProgressChangedEventArgs e) {
            ProgressBarValue = e.ProgressPercentage;
            ProgressBarText = (string)e.UserState;
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
            string packageName = BflowPackageName; // überprüfen

#if  DEBUG
            packageName = "Test.zip";
#endif

            ExtractExistingFileAction fileAction = ExtractExistingFileAction.Throw;
            if (_doOverwrite) {
                fileAction = ExtractExistingFileAction.OverwriteSilently;
            } else {
                return;
            }

            using (Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.zippacks." + packageName)) {
                if (stream == null) throw new InvalidOperationException("Could not open stream to package");
                ProgressVisibility = Visibility.Visible;

                using (ZipFile zipFile = ZipFile.Read(stream)) {
                    int fileCount = 0;
                    int filesMax = 0;
                    foreach (ZipEntry i in zipFile) {
                        filesMax++;
                    }
                    worker.ReportProgress(0, string.Format("Kopiere 0/{0} Dateien", filesMax));
                    while (fileCount < filesMax) {
                        foreach (ZipEntry zipEntry in zipFile) {

                            // Hold on if the worker is paused
                            while (_pauseBackgroundworker) {
                                Thread.Sleep(500);
                            }

                            if (worker.CancellationPending == false) {
#if DEBUG
                                // Give some time to interact
                                Thread.Sleep(200);
#endif
                                zipEntry.Extract(TargetPath, fileAction);
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
            ProgressBarText = "Installation abgeschlossen.";
        }

        public event PropertyChangedEventHandler PropertyChanged;

        private void RaisePropertyChanged(string property) {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(property));
        }
    }
}
