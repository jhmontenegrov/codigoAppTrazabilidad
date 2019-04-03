function solo_texto(e) {
    var code;
    if (!e) {
        var e = window.event;
    }
    if (e.keyCode) {
        code = e.keyCode;
    } else if (e.which) {
        code = e.which;
        var character = String.fromCharCode(code);
        var AllowRegex = /^[\a-z\s-]$/;
        if (AllowRegex.test(character)) {
            return true;
        }
    }
    return false;
};
var z = document.getElementById('ubica');
solo_texto(z);