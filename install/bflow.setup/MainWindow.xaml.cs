using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;
using Ionic.Zip;
using IWshRuntimeLibrary;
using System.Threading;
using System.ComponentModel;

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

        private void CloseButtonClick(object sender, RoutedEventArgs e)
        {
            Close();
        }

        /*public string GetPath()
        {
            string path = this.path.Text;
            return path;
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
            string path = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "bflow Toolbox" + ".lnk");
            WshShellClass shell = new WshShellClass();
            WshShortcut shortcut = (WshShortcut)shell.CreateShortcut(path);
            string targetPath = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox\\Technikspezialisierung.docx";
            shortcut.TargetPath = targetPath;
            shortcut.WorkingDirectory = Path.GetDirectoryName(targetPath);
            shortcut.Description = "bflow Toolbox";
            shortcut.IconLocation = "C:\\Users\\tschiessl\\Documents\\Quellcode\\bflow\\install\\bflow.setup\\img\\bee.ico";
            shortcut.Save();
        }*/

        private void DoZipClick(object sender, RoutedEventArgs e) {
            progBar.Value = 0;
            //lbResults.Items.Clear();
            BackgroundWorker worker = new BackgroundWorker();
            worker.WorkerReportsProgress = true;
            worker.DoWork += worker_DoWork;
            worker.ProgressChanged += worker_ProgressChanged;
            worker.RunWorkerCompleted += worker_RunWorkerCompleted;
            worker.RunWorkerAsync(10000);
        }

        void worker_DoWork(object sender, DoWorkEventArgs e)
        {
            using (ZipFile zipFile = ZipFile.Read("C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox.zip")) {
                string dir = "C:\\Users\\tschiessl\\Documents\\bflow\\bflow Toolbox";
                if (Directory.Exists(dir) && Directory.GetFiles(dir).Length > 0)
                {
                    MessageBoxResult result = System.Windows.MessageBox.Show("Es existiert bereits ein Verzeichnis! Soll das Verzeichnis überschrieben werden?", "bflow* Toolbox 1.5.0", MessageBoxButton.YesNo);
                    switch (result)
                    {
                        case MessageBoxResult.Yes:
                            int extractedFiles = 0;
                            foreach (ZipEntry i in zipFile)
                            {
                                //i.Extract(GetPath(), ExtractExistingFileAction.OverwriteSilently);
                                i.Extract("C:\\Users\\tschiessl\\Documents\\bflow", ExtractExistingFileAction.OverwriteSilently);
                                extractedFiles++;
                                int progressPercentage = extractedFiles / 2; //2 muss durch die Anzahl der Dateien ersetzt werden
                                (sender as BackgroundWorker).ReportProgress(progressPercentage);
                                System.Threading.Thread.Sleep(1);
                            }
                            /*CreateIni();
                            CreateShortcut();*/
                            //System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                            //Close();
                            break;
                        case MessageBoxResult.No:
                            System.Windows.MessageBox.Show("Die Installation wird abgebrochen.", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                            Close();
                            break;
                    }
                }
                else
                {
                    int extractedFiles = 0;
                    foreach (ZipEntry i in zipFile)
                    {
                        //i.Extract(GetPath());
                        i.Extract("C:\\Users\\tschiessl\\Documents\\bflow");
                        extractedFiles++;
                        int progressPercentage = extractedFiles / 2; //2 muss durch die Anzahl der Dateien ersetzt werden
                        (sender as BackgroundWorker).ReportProgress(progressPercentage);
                        System.Threading.Thread.Sleep(1);
                    }
                    /*CreateIni();
                    CreateShortcut();*/
                    //System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
                    //Close();
                }
            }
        }

        void worker_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progress.Visibility = Visibility.Visible;
            progBar.Value = e.ProgressPercentage;
            /*if (e.UserState != null)
                lbResults.Items.Add(e.UserState);*/ 
        }

        void worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            System.Windows.MessageBox.Show("Die Installation war erfolgreich!", "bflow* Toolbox 1.5.0", MessageBoxButton.OK);
            //Close();
        }
    }
}
