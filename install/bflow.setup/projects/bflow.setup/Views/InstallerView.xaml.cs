using System.Windows;
using System.Windows.Controls;

namespace bflow.setup.Views {
    /// <summary>
    /// Interaction logic for InstallerView.xaml
    /// </summary>
    public partial class InstallerView : UserControl {
        public InstallerView() {
            InitializeComponent();
            //this.DataContext = new bflow.setup.ViewModel.InstallerViewModel();
        }

        private void OnHandleCloseButtonClick(object sender, RoutedEventArgs e) {
            //FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            //folderBrowserDialog.RootFolder = Environment.SpecialFolder.Desktop;
            //DialogResult result = folderBrowserDialog.ShowDialog();
            //string selectedPath = folderBrowserDialog.SelectedPath;
            //installerWindow.TargetPath = selectedPath;
            //if (result == System.Windows.Forms.DialogResult.Cancel) {
            //    installerWindow.TargetPath = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
            //}
            System.Windows.MessageBox.Show("Hello!");
        }
    }
}