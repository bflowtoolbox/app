﻿using System;
using System.ComponentModel;
using System.Windows;

namespace bflow.setup.VML {

    public static class ViewModelLocator {

        public static bool GetAutoHookedUpViewModel(DependencyObject obj) {
            return (bool)obj.GetValue(AutoHookedUpViewModelProperty);
        }

        public static void SetAutoHookedUpViewModel(DependencyObject obj, bool value) {
            obj.SetValue(AutoHookedUpViewModelProperty, value);
        }

        // Using a DependencyProperty as the backing store for AutoHookedUpViewModel. 

        //This enables animation, styling, binding, etc...
        public static readonly DependencyProperty AutoHookedUpViewModelProperty =
           DependencyProperty.RegisterAttached("AutoHookedUpViewModel",
           typeof(bool), typeof(ViewModelLocator), new
           PropertyMetadata(false, AutoHookedUpViewModelChanged));

        private static void AutoHookedUpViewModelChanged(DependencyObject d,
           DependencyPropertyChangedEventArgs e) {
            if (DesignerProperties.GetIsInDesignMode(d)) return;
            var viewType = d.GetType();

            string str = viewType.FullName;
            str = str.Replace(".Views.", ".ViewModel.");

            var viewTypeName = str;
            var viewModelTypeName = viewTypeName + "Model";
            var viewModelType = Type.GetType(viewModelTypeName);
            var viewModel = Activator.CreateInstance(viewModelType);

            ((FrameworkElement)d).DataContext = viewModel;
        }
    }
}