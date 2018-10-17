using System.Windows;

namespace bflow.setup {
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {

        public MainWindow() {
            InitializeComponent();
        }

        private void InstallerViewControl_Loaded(object sender, RoutedEventArgs e) {
            bflow.setup.ViewModel.InstallerViewModel installerViewModelObject =
               new bflow.setup.ViewModel.InstallerViewModel();
            installerViewModelObject.LoadInstaller();

            //InstallerViewControl.DataContext = installerViewModelObject;
        }
    }
}
