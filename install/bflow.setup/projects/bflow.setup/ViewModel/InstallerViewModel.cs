using System;
using System.Collections.ObjectModel;
using System.Windows;
using bflow.setup.Model;
using static bflow.setup.Model.InstallerModel;

namespace bflow.setup.ViewModel {
    public class InstallerViewModel {

        public MyICommand CloseCommand { get; set; }

        public InstallerViewModel() {
            LoadInstaller();
            CloseCommand = new MyICommand(OnClose);
        }

        public ObservableCollection<Installer> Installer {
            get;
            set;
        }

        public void LoadInstaller() {
            ObservableCollection<Installer> installer = new ObservableCollection<Installer>();

            installer.Add(new Installer {
                TargetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles),
                TargetLang = "Deutsch",
                CloseButtonText = "Schließen",
                ProgressVisibility = Visibility.Hidden,
                TextboxPathIsEnabled = true,
                SelectLanguageIsEnabled = true,
                BrowseButtonIsEnabled = true,
                InstallButtonIsEnabled = true
            });

            Installer = installer;
        }

        private void OnClose() {
            MessageBox.Show("Hello");
        }
    }
}
