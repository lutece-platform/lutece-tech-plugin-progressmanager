
function getProgress( id, feedtoken ) {
    $.ajax({
        url: 'rest/progressmanager/api/v1/progressfeed/'+feedtoken,
        method: 'GET',
        async: true,
        success: function(data) {
        	updateProgressBar( id, parseInt(data.result) );
        },
        error : function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
        }
    });
}

function getReport( id, feedtoken ) {
    $.ajax({
        url: 'rest/progressmanager/api/v1/progressfeed/'+ feedtoken +'/report',
        method: 'GET',
        async: true,
        success: function(data) {
                updateProgressReport( id, data.result );
        },
        error : function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
        }
    });
}

function updateProgressBar(id, percentage){
    $('#'+id).attr('value', percentage);
    $('#'+id).html( percentage+' /'+ getProgressMaxValue( id ) );
}

function updateProgressReport(id, report, isAppend) {
    if ( !isAppend ) {
        $('#'+id+"-report").html( report );
    } else {
        $('#'+id+"-report").append( report );
    }
}

function getProgressMaxValue( id ){
	 return parseInt($('#'+id).attr('max'));
}

function getProgressValue( id ){
	 return parseInt($('#'+id).attr('value'));
}



function processError( id, errorMsg ) {
    updateProgressReport( id, errorMsg, true );
}


$(document).ready( function( ) {
    $('.progressmanager').each( function() {
            // loop
            var id = $( this ).attr( "id" );
            var token = $( this ).attr( "token" );
            var showReport = $( this ).attr( "showReport" );
            var intervalTime = $( this ).attr( "intervalTime" );
            
            interval = setInterval( function () 
                        {
                            if ( getProgressValue( id ) < getProgressMaxValue( id ) ) {

                                getProgress( id, token );

                                if ( showReport ) {
                                    getReport( id, token );
                                }
                            }
                        }
            , intervalTime );
    });
        
});    
