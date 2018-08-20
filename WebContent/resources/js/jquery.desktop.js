//
// Namespace - Module Pattern.
//
var JQD = (function($, window, document, undefined) {
  // Expose innards of JQD.
  return {
    go: function() {
      for (var i in JQD.init) {
    	// alert('1');
        JQD.init[i]();
      }
    },
    init: {
      frame_breaker: function() {
    		//alert('2');
        if (window.location !== window.top.location) {
          window.top.location = window.location;
        }
      },
      //
      // Initialize the clock.
      //
      clock: function() {
    	 // alert('3');
        if (!$('#clock').length) {        	
          return;
        }

      　　　　　　　

        // Shove in the HTML.
        $('#clock').html(clock_time).attr('title', clock_date);

        // Update every 60 seconds.
        setTimeout(JQD.init.clock, 1000);
      },
	  //
	  // 初始化开始菜单
	  //
	  initStartMenu: function(){
    	 // alert('4');
		 $('#AlloyStartButton').on('click', function() {
			 $('#startMenuContainer').addClass('taskbar_start_menu_container_show');
		 });
		　
	  },
      //
      // Initialize the desktop.
      //
      desktop: function() {
        // Cancel mousedown, right-click.
		//  alert('5');
        $(document).mousedown(function(ev) {
          var tags = ['a', 'button', 'input', 'select', 'textarea'];

          if (!$(ev.target).closest(tags).length) {
            JQD.util.clear_active();
          
            ev.stopPropagation();
          }
        }).bind('contextmenu', function() {
          return true;
        });

        // Relative or remote links?
   /*     $('a').live('click', function(ev) {
          var url = $(this).attr('href');
          this.blur();

          if (url.match(/^#/)) {
            ev.preventDefault();
            ev.stopPropagation();
          }
          else {
            $(this).attr('target', '_blank');
          }
        });*/

        // Make top menus active.
        $('a.menu_trigger').on('mousedown', function() {
          if ($(this).next('ul.menu').is(':hidden')) {
            JQD.util.clear_active();
            $(this).addClass('active').next('ul.menu').show();
          }
          else {
            JQD.util.clear_active();
          }
        }).on('mouseenter', function() {
          // Transfer focus, if already open.
          if ($('ul.menu').is(':visible')) {
            JQD.util.clear_active();
            $(this).addClass('active').next('ul.menu').show();
          }
        });

    　

	        // Taskbar buttons.
	        $('#dock a').on('click', function() {
	          // Get the link's target.
	          var x = $($(this).attr('href'));
	          // Hide, if visible.
	          if (x.is(':visible')) {
	            x.hide();
	          }
	          else {
	            // Bring window to front.
	            JQD.util.window_flat();
	            x.show().addClass('window_stack');
	          }
	        });

        // Make windows movable.
        $('div.window').on('mousedown', function() {
          // Bring window to front.
          JQD.util.window_flat();
          $(this).addClass('window_stack');
        }).on('mouseenter', function() {
          　

        // Double-click top bar to resize, ala Windows OS.
        }).find('div.window_top').on('dblclick', function() {
          JQD.util.window_resize(this);

        // Double click top bar icon to close, ala Windows OS.
        }).find('img').on('dblclick', function() {
          // Traverse to the close button, and hide its taskbar button.
          $($(this).closest('div.window_top').find('a.window_close').attr('href')).hide('fast');

          // Close the window itself.
          $(this).closest('div.window').hide();

          // Stop propagation to window's top bar.
          return false;
        });

        // Minimize the window.
        $('a.window_min').on('click', function() {
          $(this).closest('div.window').hide();
        });

        // Maximize or restore the window.
        $('a.window_resize').on('click', function() {
          JQD.util.window_resize(this);
        });

        // Close the window.
        $('a.window_close').on('click', function() {
          $(this).closest('div.window').hide();
          $($(this).attr('href')).hide('fast');
        });

        // Show desktop button, ala Windows OS.
        $('#show_desktop').on('mousedown', function() {
          // If any windows are visible, hide all.
          if ($('div.window:visible').length) {
            $('div.window').hide();
          }
          else {
            // Otherwise, reveal hidden windows that are open.
            $('#dock li:visible a').each(function() {
              $($(this).attr('href')).show();
            });
          }
        });

        $('table.data').each(function() {
          // Add zebra striping, ala Mac OS X.
          $(this).find('tbody tr:odd').addClass('zebra');
        }).find('tr').on('mousedown', function() {
          // Highlight row, ala Mac OS X.
          $(this).closest('tr').addClass('active');
        });
      },
    
    },
    util: {
      
      clear_active: function() {
    	//alert('6');
        $('a.active, tr.active').removeClass('active');
        $('ul.menu').hide();
		$('#startMenuContainer').removeClass('taskbar_start_menu_container_show');
      },
    
      window_flat: function() {
    	//  alert('7');
        $('div.window').removeClass('window_stack');
      },
     
   
    }
  };
// Pass in jQuery.
})(jQuery, this, this.document);

//
// Kick things off.
//
jQuery(document).ready(function() {
//	alert('8');
  JQD.go();
 // cloudMove();
//  $("#login").click(function() {
//	 	// alert('9');
//		JQD.util.clear_active();
//		 
//  });
});
 