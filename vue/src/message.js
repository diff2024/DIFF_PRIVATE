const sendMessage = (message) => {
    const userAgent = navigator.userAgent.toLowerCase();
    
    if (userAgent.indexOf('android') !== -1) {
      return WebViewBridge.showMessage(message);
    }
  }