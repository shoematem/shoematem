function getClickedOrderForMatch(orderType)
{
    switch(orderType)
    {
        case 'buy':
            $('#tableBuy tbody tr').on('click', function(event) {
                $(this).addClass('highlight').siblings().removeClass('highlight');
            });
            break;
        case 'sell':
            $('#tableSell tbody tr').on('click', function(event) {
                $(this).addClass('highlight').siblings().removeClass('highlight');
            });
            break
        default:
            break;
    }
}

function showHiddenDiv()
{
    var container = document.getElementById('hideUntilSelect');
    var display = window.getComputedStyle(container).display;

    if(display === 'none')
    {
        $('#hideUntilSelect').show();
    }
}

function popUpOpen()
{
    document.getElementById("popup").style.display = "block";
    document.getElementById("overlay").style.display = "block";
}

function popUpClose()
{
    document.getElementById("popup").style.display = "none";
    document.getElementById("overlay").style.display = "none";
}