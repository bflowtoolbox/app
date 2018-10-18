using System;
using System.ComponentModel;
using System.Windows;
using System.Windows.Input;

namespace bflow.setup {
    public class MainWindowViewModel : INotifyPropertyChanged {
        private string _targetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
        private string _language = "Deutsch";
        private bool _textboxPathIsEnabled = true;
        private Visibility _progressVisibility { get; set; } = Visibility.Hidden;
        private bool _selectLanguageIsEnabled = true;
        private bool _browseButtonIsEnabled = true;
        private bool _installButtonIsEnabled = true;

        public ICommand CloseCommand { get; set; } = new CloseCommand();

        public string TargetPath {
            get {
                return _targetPath;
            }

            set {
                if (_targetPath != value) {
                    _targetPath = value;
                    RaisePropertyChanged("TargetPath");
                }
            }
        }

        public string Language {
            get { return _language; }

            set {
                if (_language != value) {
                    _language = value;
                    RaisePropertyChanged("Language");
                }
            }
        }

        public Visibility ProgressVisibility {
            get { return _progressVisibility; }

            set {
                if (_progressVisibility != value) {
                    _progressVisibility = value;
                    RaisePropertyChanged("ProgressVisibility");
                }
            }
        }

        public bool TextboxPathIsEnabled {
            get { return _textboxPathIsEnabled; }

            set {
                if (_textboxPathIsEnabled != value) {
                    _textboxPathIsEnabled = value;
                    RaisePropertyChanged("TextboxPathIsEnabled");
                }
            }
        }

        public bool SelectLanguageIsEnabled {
            get { return _selectLanguageIsEnabled; }

            set {
                if (_selectLanguageIsEnabled != value) {
                    _selectLanguageIsEnabled = value;
                    RaisePropertyChanged("SelectLanguageIsEnabled");
                }
            }
        }

        public bool BrowseButtonIsEnabled {
            get { return _browseButtonIsEnabled; }

            set {
                if (_browseButtonIsEnabled != value) {
                    _browseButtonIsEnabled = value;
                    RaisePropertyChanged("BrowseButtonIsEnabled");
                }
            }
        }

        public bool InstallButtonIsEnabled {
            get { return _installButtonIsEnabled; }

            set {
                if (_installButtonIsEnabled != value) {
                    _installButtonIsEnabled = value;
                    RaisePropertyChanged("InstallButtonIsEnabled");
                }
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        private void RaisePropertyChanged(string property) {
            if (PropertyChanged != null) {
                PropertyChanged(this, new PropertyChangedEventArgs(property));
            }
        }
    }
}
