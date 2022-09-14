
function main() {

    (function () {
        'use strict';

        $('a.page-scroll').click(function() {
            if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top - 50
                    }, 900);
                    return false;
                }
            }
        });


        $('body').scrollspy({
            target: '.navbar-default',
            offset: 80
        });

        // Hide nav on click
        $(".navbar-nav li a").click(function (event) {
            // check if window is small enough so dropdown is created
            var toggle = $(".navbar-toggle").is(":visible");
            if (toggle) {
                $(".navbar-collapse").collapse('hide');
            }
        });


        // Nivo Lightbox
        $('.portfolio-item a').nivoLightbox({
            effect: 'slideDown',
            keyboardNav: true,
        });

    }());


}
main();


function changeColor() {
    var color = ["#FC5C7D", "#6A82FB", "#38ef7d", "#fffbd5", "#b20a2c", "#CAC531"];

    var num = Math.floor(Math.random() * color.length);
    var bodyTag = document.getElementsByTagName("td");
    bodyTag[0].style.backgroundColor = color[num];
}