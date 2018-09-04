using System;
using System.Windows;
using Ionic.Zip;

namespace bflow.setup {
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void OnKlickMichClick(object sender, RoutedEventArgs e) {
            Console.WriteLine("Ich wurde geklickt");
        }

        private void DoZip() {
            using (ZipFile zipFile = ZipFile.Read("foo.zip")) {
                // TODO extract
            }
        }
    }
}
