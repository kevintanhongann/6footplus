function showSpinner() {
    $('spinner').show();
}

function hideSpinner() {
    $('spinner').hide();
}

Ajax.Responders.register({
    onLoading: function() {
        showSpinner();
    },
    onComplete: function() {
        if(!Ajax.activeRequestCount) hideSpinner();
    }
});
