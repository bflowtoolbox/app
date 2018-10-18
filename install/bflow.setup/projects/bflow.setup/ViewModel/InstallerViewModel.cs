using System;
using System.Collections.ObjectModel;
using System.Windows;
using System.Windows.Input;
using bflow.setup.Model;
using static bflow.setup.Model.InstallerModel;

namespace bflow.setup.ViewModel {
    public class InstallerViewModel {

        public ICommand CloseCommand { get; set; } = new MyCommand();

        public InstallerViewModel() {
            LoadInstaller();
            //CloseCommand = new MyCommand();
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
    }
}
