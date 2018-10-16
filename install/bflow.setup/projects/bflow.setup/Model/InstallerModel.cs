using System.ComponentModel;
using System.Windows;

namespace bflow.setup.Model {
    public class InstallerModel {
        public class Installer : INotifyPropertyChanged {
            private string _targetPath;
            private string _targetLang;
            private string _closeButtonText;
            private Visibility _progressVisibility;
            private bool _textboxPathIsEnabled;
            private bool _selectLanguageIsEnabled;
            private bool _browseButtonIsEnabled;
            private bool _installButtonIsEnabled;

            public string TargetPath {
                get { return _targetPath; }
                set {
                    _targetPath = value;
                    RaisePropertyChanged("TargetPath");
                    //Notify("TargetPath");
                }
            }

            public string TargetLang {
                get { return _targetLang; }
                set {
                    _targetLang = value;
                    RaisePropertyChanged("TargetLang");
                    //Notify("TargetLang");
                }
            }

            public string CloseButtonText {
                get { return _closeButtonText; }
                set {
                    _closeButtonText = value;
                    RaisePropertyChanged("CloseButtonText");
                    //Notify("CloseButtonText");
                }
            }

            public Visibility ProgressVisibility {
                get { return _progressVisibility; }
                set {
                    _progressVisibility = value;
                    RaisePropertyChanged("ProgressVisibility");
                    //Notify("ProgressVisibility");
                }
            }

            public bool TextboxPathIsEnabled {
                get { return _textboxPathIsEnabled; }
                set {
                    _textboxPathIsEnabled = value;
                    RaisePropertyChanged("TextboxPathIsEnabled");
                    //Notify("TextboxPathIsEnabled");
                }
            }

            public bool SelectLanguageIsEnabled {
                get { return _selectLanguageIsEnabled; }
                set {
                    _selectLanguageIsEnabled = value;
                    RaisePropertyChanged("SelectLanguageIsEnabled");
                    //Notify("SelectLanguageIsEnabled");
                }
            }

            public bool BrowseButtonIsEnabled {
                get { return _browseButtonIsEnabled; }
                set {
                    _browseButtonIsEnabled = value;
                    RaisePropertyChanged("BrowseButtonIsEnabled");
                    //Notify("BrowseButtonIsEnabled");
                }
            }

            public bool InstallButtonIsEnabled {
                get { return _installButtonIsEnabled; }
                set {
                    _installButtonIsEnabled = value;
                    RaisePropertyChanged("InstallButtonIsEnabled");
                    //Notify("InstallButtonIsEnabled");
                }
            }

            public event PropertyChangedEventHandler PropertyChanged;

            private void RaisePropertyChanged(string property) {
                PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(property));
            }
        }

    }
}
