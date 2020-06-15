using System.Windows;
using System.Windows.Input;

namespace bflow.setup {
    /// <summary>
    /// Implementiert eine Trigger-Action, die innerhalb von XAML-Code dazu verwendet wird,
    /// um Events eines Framework-Elements an ein Command zu binden. Dies ermöglicht
    /// beispielsweise das Binden eines Commands an ein Doppelklick-Event einer List-View.
    /// </summary>
    public class EventToCommand : System.Windows.Interactivity.TriggerAction<FrameworkElement> {
        /// <summary>
        /// Command, der beim Auftreten des Events ausgeführt wird.
        /// </summary>
        public ICommand Command {
            get { return (ICommand)GetValue(CommandProperty); }
            set { SetValue(CommandProperty, value); }
        }

        /// <summary>
        /// Dependency-Property für den Command.
        /// </summary>
        public static readonly DependencyProperty CommandProperty =
            DependencyProperty.Register("Command", typeof(ICommand), typeof(EventToCommand), new UIPropertyMetadata(null));

        /// <summary>
        /// Dependency-Property für den UseEventParameter-Flag
        /// </summary>
        public static readonly DependencyProperty UseEventParameterProperty =
            DependencyProperty.Register("UseEventParameter", typeof(bool), typeof(EventToCommand), new PropertyMetadata(default(bool)));

        /// <summary>
        /// Gibt an, ob die Event-Parameter als Command-Parameter genutzt werden sollen oder legt das fest.
        /// </summary>
        public bool UseEventParameter {
            get { return (bool)GetValue(UseEventParameterProperty); }
            set { SetValue(UseEventParameterProperty, value); }
        }

        /// <summary>
        /// Command Parameter
        /// </summary>
        public object CommandParameter {
            get { return GetValue(CommandParameterProperty); }
            set { SetValue(CommandParameterProperty, value); }
        }

        // Using a DependencyProperty as the backing store for CommandParameter.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty CommandParameterProperty =
            DependencyProperty.Register("CommandParameter", typeof(object), typeof(EventToCommand), new UIPropertyMetadata(null));

        protected override void Invoke(object parameter) {
            if (Command == null) {
                return;
            }

            // Sollen explizit die Event-Parameter durchgeroutet werden?
            if (UseEventParameter) {
                CommandParameter = parameter;
            }

            if (Command is RoutedCommand) {
                RoutedCommand routedCommand = Command as RoutedCommand;

                if (routedCommand.CanExecute(CommandParameter, AssociatedObject)) {
                    routedCommand.Execute(CommandParameter, AssociatedObject);
                }
            } else {
                if (Command.CanExecute(CommandParameter)) {
                    Command.Execute(CommandParameter);
                }
            }
        }
    }
}
