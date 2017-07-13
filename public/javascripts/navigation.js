$(document).ready(function(){

    //Navigation Menu Slider
    $('#nav-expander').on('click',function(e){
        e.preventDefault();
        $('body').toggleClass('nav-expanded');
        $('.navbar-header').css('display', 'none');
    });
    $('#nav-close').on('click',function(e){
        e.preventDefault();
        $('body').removeClass('nav-expanded');
        $('.navbar-header').css('display', 'block');
    });
    $('body').on('click', function(e){
        //console.log(e.target);
        if(!$(e.target).is('#navTop') && !$(e.target).is('#sideNav')){
            $('body').removeClass('nav-expanded');
            $('.navbar-header').css('display', 'block');
        }
    });

});
