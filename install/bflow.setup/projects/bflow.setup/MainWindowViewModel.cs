using Ionic.Zip;
using IWshRuntimeLibrary;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Forms;
using System.Windows.Input;
using Application = System.Windows.Application;
using File = System.IO.File;

namespace bflow.setup {
    public class MainWindowViewModel : INotifyPropertyChanged {
        private const string UnixCarriageReturn = "\n";
        private const string WindowsCarriageReturn = "\r\n";
        private const string BflowPackageName = "bflow.zip";

        private static readonly string _tempPath = Path.GetTempPath(); // Temppath
        private static readonly string _tempInstallPath = Path.Combine(_tempPath, "bflow");

        private string _targetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles) + "\\bflow";
        private string _groupTargetPath = string.Empty;
        private string _language = "Deutsch";
        private string _closeButtonText = "Schließen";
        private string _progressBarText = string.Empty;
        private int _progressBarValue;
        private bool _progressBarIndeterminate;
        private bool _textboxPathIsEnabled = true;
        private bool _textboxGroupPathIsEnabled;
        private Visibility _progressVisibility = Visibility.Hidden;
        private bool _selectLanguageIsEnabled = true;
        private bool _browseButtonIsEnabled = true;
        private bool _browseGroupButtonIsEnabled;
        private bool _installButtonIsEnabled = true;
        private bool _closeButtonIsEnabled = true;
        private bool _checkboxGroupPathIsEnabled = true;
        private bool _checkboxGroupPathIsChecked;
        
        private bool _doOverwrite;
        private bool _hasInstallStarted;
        private bool _hasInstallFinished;
        private bool _isInstallSuccess;
        private bool _pauseBackgroundworker;
        private string _iniLanguage = string.Empty;
        private BackgroundWorker _worker;

        public MainWindowViewModel() {
            CloseCommand = new RelayCommand(OnExecuteClose, OnCanExecuteClose);
            BrowseCommand = new RelayCommand(OnExecuteBrowse, OnCanExecuteBrowse);
            BrowseGroupCommand = new RelayCommand(OnExecuteBrowseGroup, OnCanExecuteBrowseGroup);
            ExitCommand = new RelayCommand(OnExecuteExit, OnCanExecuteExit);
            InstallCommand = new RelayCommand(OnExecuteInstall, OnCanExecuteInstall);
            CheckGroupCommand = new RelayCommand(OnExecuteCheckGroup, OnCanExecuteCheckGroup);
        }

        public ICommand CloseCommand { get; }

        public ICommand ExitCommand { get; }

        public ICommand BrowseCommand { get; }

        public ICommand BrowseGroupCommand { get; }

        public ICommand InstallCommand { get; }

        public ICommand CheckGroupCommand { get; }

        public string TargetPath {
            get { return _targetPath; }
            set {
                if (_targetPath != value) {
                    _targetPath = value;
                    RaisePropertyChanged();
                }
            }
        }

        public string GroupTargetPath {
            get { return _groupTargetPath; }
            set {
                if (_groupTargetPath != value) {
                    _groupTargetPath = value;
                    RaisePropertyChanged();
                }
            }
        }

        public string Language {
            get { return _language; }
            set {
                if (_language != value) {
                    _language = value;
                    RaisePropertyChanged();
                }
            }
        }

        public string CloseButtonText {
            get { return _closeButtonText; }
            set {
                if (_closeButtonText != value) {
                    _closeButtonText = value;
                    RaisePropertyChanged();
                }
            }
        }

        public string ProgressBarText {
            get { return _progressBarText; }
            set {
                if (_progressBarText != value) {
                    _progressBarText = value;
                    RaisePropertyChanged();
                }
            }
        }

        public int ProgressBarValue {
            get { return _progressBarValue; }
            set {
                if (_progressBarValue != value) {
                    _progressBarValue = value;
                    RaisePropertyChanged();
                }
            }
        }

        public Visibility ProgressVisibility {
            get { return _progressVisibility; }
            set {
                if (_progressVisibility != value) {
                    _progressVisibility = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool TextboxPathIsEnabled {
            get { return _textboxPathIsEnabled; }
            set {
                if (_textboxPathIsEnabled != value) {
                    _textboxPathIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool ProgressBarIndeterminate {
            get { return _progressBarIndeterminate; }
            set {
                if (_progressBarIndeterminate != value) {
                    _progressBarIndeterminate = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool TextboxGroupPathIsEnabled {
            get { return _textboxGroupPathIsEnabled; }
            set {
                if (_textboxGroupPathIsEnabled != value) {
                    _textboxGroupPathIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool SelectLanguageIsEnabled {
            get { return _selectLanguageIsEnabled; }
            set {
                if (_selectLanguageIsEnabled != value) {
                    _selectLanguageIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool BrowseButtonIsEnabled {
            get { return _browseButtonIsEnabled; }
            set {
                if (_browseButtonIsEnabled != value) {
                    _browseButtonIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool BrowseGroupButtonIsEnabled {
            get { return _browseGroupButtonIsEnabled; }
            set {
                if (_browseGroupButtonIsEnabled != value) {
                    _browseGroupButtonIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool InstallButtonIsEnabled {
            get { return _installButtonIsEnabled; }
            set {
                if (_installButtonIsEnabled != value) {
                    _installButtonIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool CloseButtonIsEnabled {
            get { return _closeButtonIsEnabled; }
            set {
                if (_closeButtonIsEnabled != value) {
                    _closeButtonIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool CheckboxGroupPathIsEnabled {
            get { return _checkboxGroupPathIsEnabled; }
            set {
                if (_checkboxGroupPathIsEnabled != value) {
                    _checkboxGroupPathIsEnabled = value;
                    RaisePropertyChanged();
                }
            }
        }

        public bool CheckboxGroupPathIsChecked {
            get { return _checkboxGroupPathIsChecked; }
            set {
                if (_checkboxGroupPathIsChecked != value) {
                    _checkboxGroupPathIsChecked = value;
                    RaisePropertyChanged();
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
            GroupTargetPath = result == DialogResult.Cancel
                ? string.Empty
                : selectedPath;
            InstallButtonIsEnabled = !string.IsNullOrEmpty(selectedPath);
        }

        private bool OnCanExecuteBrowseGroup(object arg) {
            return true;
        }

        private static bool OnCanExecuteClose(object arg) {
            return true;
        }

        private void OnExecuteClose(object obj) {
            if (!CloseButtonIsEnabled) return;
            Application.Current?.MainWindow?.Close();
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
            if (CloseButtonIsEnabled) {
                if (!_hasInstallStarted && !_hasInstallFinished && !_isInstallSuccess) {
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
                } else {
                    eventArgs.Cancel = true;
                    _pauseBackgroundworker = true;
                    MessageBoxResult result = System.Windows.MessageBox.Show("Möchten Sie die Installation abbrechen?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                    _pauseBackgroundworker = false;
                    switch (result) {
                        case MessageBoxResult.Yes:
                            _worker.CancelAsync();
                            ProgressVisibility = Visibility.Hidden;
                            _hasInstallStarted = false;
                            ChangeCloseButtonText();
                            break;
                        case MessageBoxResult.No:
                            return;
                    }
                }
            } else {
                eventArgs.Cancel = true;
            }
        }

        /// <summary>
        /// While the installation is running, the text on the Close Button shows "Abbrechen",
        /// otherwise it shows "Schließen"
        /// </summary>
        private void ChangeCloseButtonText() {
            if (_hasInstallStarted && !_hasInstallFinished && !_isInstallSuccess) {
                CloseButtonText = "Abbrechen";
            } else {
                CloseButtonText = "Schließen";
            }
        }

        /// <summary>
        /// Creates a new Ini file in the installation folder with the content of the field _iniText
        /// </summary>
        private void CreateIni() {
            Dictionary<string, string> iniValueMap = new Dictionary<string, string>();
            
            string iniPath = TargetPath + "\\bflow.ini";
            string oldIniText = File.ReadAllText(iniPath);
            string carriageReturn = oldIniText.Contains(WindowsCarriageReturn)
                ? WindowsCarriageReturn
                : UnixCarriageReturn;

            string[] oldIniLines = oldIniText.Split(new[] { carriageReturn }, StringSplitOptions.RemoveEmptyEntries);
            for (int i = 0; i < oldIniLines.Length; i += 2) {
                string key = oldIniLines[i];
                string value = oldIniLines[i + 1];
                iniValueMap.Add(key, value);
            }
            // There are two cases: Either the language key does already exist, than the value can simply be overwritten.
            // Or the language key doesn't exist (shouldn't be happening!), than the key-value-pair is added.
            iniValueMap["-nl"] = _iniLanguage;
            if (GroupTargetPath != string.Empty) {
                // As seen above
                iniValueMap["--sharedfolder"] = GroupTargetPath;
            }
            IEnumerable<string> entryList = iniValueMap.Select(pair => pair.Key + carriageReturn + pair.Value);
            string newIniText = string.Join(carriageReturn, entryList);
            File.WriteAllText(iniPath, newIniText);
        }

        /// <summary>  Creates a shortcut on the desktop with an icon and text. </summary>
        private void CreateShortcut() {
            string shortcutPath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(shortcutPath);

            string exeName = "bflow.exe";

#if DEBUG
            exeName = "Technikspezialisierung.docx";
#endif

            string targetPath = TargetPath + "\\" + exeName;
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            string iconLocation = TargetPath + "\\beeDesk.ico"; // global??
            using (Stream resource = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.images.beeDesk.ico")) {
                if (resource == null) throw new InvalidOperationException("Could not resolve icon from manifest");
                using (FileStream file = new FileStream(iconLocation, FileMode.Create, FileAccess.Write)) {
                    resource.CopyTo(file);
                }
            }
            shortcut.IconLocation = iconLocation;
            shortcut.Save();
        }

        /// <summary> Enables or disabled the UI for user input </summary>
        private void EnableUI(bool value) {
            TextboxPathIsEnabled = value;
            SelectLanguageIsEnabled = value;
            BrowseButtonIsEnabled = value;
            BrowseButtonIsEnabled = value;
            InstallButtonIsEnabled = value;
            CheckboxGroupPathIsEnabled = value;
            if (CheckboxGroupPathIsChecked) {
                TextboxGroupPathIsEnabled = value;
                BrowseGroupButtonIsEnabled = value;
            } else {
                TextboxGroupPathIsEnabled = false;
                BrowseGroupButtonIsEnabled = false;
            }

        }

        private bool OnCanExecuteInstall(object arg) {
            if (Language == "Deutsch") {
                _iniLanguage = "de_DE";
                return true;
            }

            if (Language == "Englisch") {
                _iniLanguage = "en_US";
                return true;
            }

            System.Windows.MessageBox.Show("Bitte geben Sie eine gültige Sprache ein!");
            return false;
        }

        private void OnExecuteInstall(object obj) {
            _hasInstallStarted = true;
            EnableUI(false);
            _doOverwrite = DoOverwrite(TargetPath);
            ChangeCloseButtonText();
            _worker = new BackgroundWorker();
            _worker.WorkerSupportsCancellation = true;
            _worker.RunWorkerCompleted += OnRunWorkerCompleted;
            _worker.WorkerReportsProgress = true;
            _worker.DoWork += OnExtractData;
            _worker.ProgressChanged += OnProgressChanged;
            _worker.RunWorkerAsync(1000);
        }

        private void OnExecuteCheckGroup(object obj) {
            if (CheckboxGroupPathIsChecked) {
                TextboxGroupPathIsEnabled = true;
                BrowseGroupButtonIsEnabled = true;
                if (GroupTargetPath == string.Empty) {
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
        private void OnProgressChanged(object sender, ProgressChangedEventArgs e) {
            ProgressBarValue = e.ProgressPercentage;
            ProgressBarText = (string)e.UserState;
        }

        /// <summary>
        /// Checks if the selected directory does already exists and asks the user if it should overwrite
        /// </summary>
        /// <param name="directory"></param>
        /// <returns></returns>
        private bool DoOverwrite(string directory) {
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
        /// After extracting the installation files to the temp folder, the files are moved to the target folder
        /// Already existing folders are deleted
        /// </summary>
        private void MoveTempToTarget() {
            if (Directory.Exists(TargetPath)) {
                Directory.Delete(TargetPath, true);
            }

            // The app has to wait so that the directory is deleted when the moving action is started
            // but not longer than 2 minutes
            int elapsedTime = 0;
            const int sleepTime = 1000;
            while (Directory.Exists(TargetPath) && elapsedTime <= 120 * sleepTime) {
                Thread.Sleep(sleepTime);
                elapsedTime += sleepTime;
            }
            
            Exception cpyEx = null;
            try {
                Win32.CopyDirectory(_tempInstallPath, TargetPath, true);
            } catch (Exception ex) {
                cpyEx = ex;
            }

            // Always clean up
            try {
                Directory.Delete(_tempInstallPath, true);
            } catch {
                // Ok, not fine but we cannot change it
            }

            if (cpyEx != null)
                throw cpyEx;
        }

        /// <summary>
        /// The DoWork event of the backgroundworker is responsible for the actual process of
        /// extracting the data to the install directory
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnExtractData(object sender, DoWorkEventArgs e) {
            string packageName = BflowPackageName;

            ExtractExistingFileAction fileAction = ExtractExistingFileAction.Throw;
            if (!_doOverwrite) return;

            fileAction = ExtractExistingFileAction.OverwriteSilently;

            using (Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("bflow.setup.zippacks." + packageName)) {
                if (stream == null) throw new InvalidOperationException("Could not open stream to package");
                ProgressVisibility = Visibility.Visible;

                using (ZipFile zipFile = ZipFile.Read(stream)) {
                    int fileCount = 0;
                    int filesMax = zipFile.Count;
                    _worker.ReportProgress(0, string.Format("Kopiere 0/{0} Dateien", filesMax));
                    while (fileCount < filesMax) {
                        foreach (ZipEntry zipEntry in zipFile) {

                            // Hold on if the worker is paused
                            while (_pauseBackgroundworker) {
                                Thread.Sleep(500);
                            }

                            if (_worker.CancellationPending == false) {
#if DEBUG
                                // Give some time to interact
                                Thread.Sleep(200);
#endif
                                zipEntry.Extract(_tempPath, fileAction);
                                int progressSteps = Convert.ToInt32(Math.Ceiling(100.00 / filesMax));
                                _worker.ReportProgress((fileCount + 1) * progressSteps, string.Format("Kopiere {0}/{1} Dateien", fileCount + 1, filesMax));
                                fileCount++;
                            } else {
                                _hasInstallStarted = false;
                                EnableUI(true);
                                return;
                            }
                        }
                    }
                    Task.Run(() => {
                        Thread.Sleep(2000);
                        if (!CloseButtonIsEnabled) {
                            Application.Current.Dispatcher.BeginInvoke(new Action(() => {
                                ProgressBarIndeterminate = true;
                                ProgressBarText = "Installation abschließen";
                                CloseButtonText = "Warten...";
                            }));
                        }
                    });
                    CloseButtonIsEnabled = false;
                    EnableUI(false);
                    try {
                        MoveTempToTarget();
                        CreateIni();
                        CreateShortcut();
                        _hasInstallFinished = true;
                        _isInstallSuccess = true;
                    } catch (Exception ex) {
                        System.Windows.MessageBox.Show(ex.Message);
                        // The Installation has started and finished but was not successfull
                        _hasInstallFinished = true;
                        _isInstallSuccess = false;
                        ChangeCloseButtonText();
                        ProgressBarText = "Installation fehlgeschlagen.";
                        ProgressBarValue = 0;
                    }
                    CloseButtonIsEnabled = true;
                    ProgressBarIndeterminate = false;
                }
            }
        }

        /// <summary>
        /// This event is fired when the backgroundworker has actually completed its tasks
        /// </summary>
        private void OnRunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e) {
            if (_hasInstallFinished && _isInstallSuccess) {
                System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                ChangeCloseButtonText();
                ProgressBarText = "Installation abgeschlossen.";
            }

            // Cleaning up the temp folder
            if (Directory.Exists(_tempInstallPath)) {
                Directory.Delete(_tempInstallPath, true);
            }
        }

        /// <inheritdoc />
        public event PropertyChangedEventHandler PropertyChanged;

        /// <summary> Raises the <see cref="PropertyChanged"/> event for the <paramref name="propertyName"/>. </summary>
        private void RaisePropertyChanged([CallerMemberName] string propertyName = null) {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
