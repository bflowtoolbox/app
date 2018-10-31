using System;
using System.Windows.Input;

namespace bflow.setup {
    class RelayCommand : ICommand {
        private readonly Func<object, bool> _canExecuteHandler;
        private readonly Action<object> _executeHandler;

        public RelayCommand(Action<object> executeHandler, Func<object, bool> canExecuteHandler) {
            _executeHandler = executeHandler;
            _canExecuteHandler = canExecuteHandler;
        }

        /// <inheritdoc />
        public event EventHandler CanExecuteChanged;

        /// <inheritdoc />
        public bool CanExecute(object parameter) {
            return _canExecuteHandler(parameter);
        }

        public void Execute(object parameter) {
            _executeHandler(parameter);
        }

        protected virtual void RaiseCanExecuteChanged() {
            CanExecuteChanged?.Invoke(this, EventArgs.Empty);
        }
    }
}
