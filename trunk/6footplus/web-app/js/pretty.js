/*
 * http://ejohn.org/blog/javascript-pretty-date#postcomment
 */

function prettyDate(time){
    var date = new Date((time || "").replace(/-/g,"/").replace(/[TZ]/g," ")),
    localDate = new Date(),
    diff = (((localDate).getTime() + (localDate.getTimezoneOffset() * 60000) - date.getTime()) / 1000),
    day_diff = Math.floor(diff / 86400);


			
    if ( isNaN(day_diff) || day_diff < 0)
        return;

    var result = day_diff == 0 && (
        diff < 60 && "just now" ||
        diff < 120 && "1 minute ago" ||
        diff < 3600 && Math.floor( diff / 60 ) + " minutes ago" ||
        diff < 7200 && "1 hour ago" ||
        diff < 86400 && Math.floor( diff / 3600 ) + " hours ago") ||
    day_diff == 1 && "Yesterday" ||
    day_diff < 7 && day_diff + " days ago" ||
    day_diff < 31 && Math.ceil( day_diff / 7 ) + " weeks ago";

    if(result == false){
        result = "months ago";
    }

    return result;
}