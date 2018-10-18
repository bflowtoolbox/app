using System;
using System.Windows.Input;

namespace bflow.setup {
    public class CloseCommand : ICommand {
        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter) {
            return true;
        }

        public void Execute(object parameter) {
            System.Windows.MessageBox.Show("Hello");
        }
    }
}
