using System;
using System.Windows;
using System.Windows.Forms;
using Ionic.Zip;

namespace bflow.setup {
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void OnOpenFolderClick(object sender, RoutedEventArgs e) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            DialogResult result = folderBrowserDialog.ShowDialog();
            String path = folderBrowserDialog.SelectedPath;
            textName.Text = path;
        }

        private void DoZip() {
            using (ZipFile zipFile = ZipFile.Read("foo.zip")) {
                // TODO extract
            }
        }
    }
}
