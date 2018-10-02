using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;
using Ionic.Zip;
using IWshRuntimeLibrary;

namespace bflow.setup {
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void GetPathClick(object sender, RoutedEventArgs e) {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            DialogResult Result = folderBrowserDialog.ShowDialog();
            string Path = folderBrowserDialog.SelectedPath;
            path.Text = Path;
        }

        public string GetPath()
        {
            string path = this.path.Text;
            return path;
        }

        private void CloseButtonClick(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void CreateIni()
        {
            string text = "-nl ";
            if(language.Text == "Deutsch")
            {
                text += "de-DE";
            }
            else if(language.Text == "Englisch")
            {
                text += "en-US";
            }
            else
            {
                //Exception
                text += "Fehler";
            }
            System.IO.File.WriteAllText(@"C:\\Users\\tschiessl\\Documents\\bflow\\bflow.ini", text);
        }

        private void CreateShortcut()
        {
            string shortcut = Environment.GetFolderPath(Environment.SpecialFolder.Desktop) + "\\bflow Toolbox.url";
            string source = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox\\Technikspezialisierung.docx";
            string icon = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow-logo.png";
            StreamWriter sw = new StreamWriter(shortcut);
            sw.WriteLine("[InternetShortcut]");
            sw.WriteLine("URL=file:///" + source);
            sw.WriteLine("IconIndex=0");
            sw.WriteLine("IconFile=" + icon);
            sw.Close();
        }


        private void DoZipClick(object sender, RoutedEventArgs e) {
            using (ZipFile zipFile = ZipFile.Read("C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox.zip")) {
                foreach (ZipEntry i in zipFile)
                {
                    i.Extract(GetPath());
                }
                CreateIni();
                CreateShortcut();
                System.Windows.MessageBox.Show("Die Installation wurde erfolgreich abgeschlossen!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                Close();
            }
        }
    }
}
