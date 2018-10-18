using System.Windows;
using System.Windows.Controls;
using bflow.setup;

namespace bflow.setup.Views {
    /// <summary>
    /// Interaction logic for InstallerView.xaml
    /// </summary>
    public partial class InstallerView : UserControl {
        public InstallerView() {
            InitializeComponent();
            this.DataContext = new bflow.setup.ViewModel.InstallerViewModel();
        }
    }
}