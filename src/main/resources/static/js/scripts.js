/* ================================================
   MONACO — scripts.js
   ================================================ */

(function () {
  function init() {

    /* ── Sidebar toggle ── */
    var toggle  = document.getElementById('sidebarToggle');
    var nav     = document.getElementById('layoutSidenav_nav');
    var content = document.getElementById('layoutSidenav_content');

    if (toggle && nav) {
      toggle.addEventListener('click', function (e) {
        e.preventDefault();
        e.stopPropagation();

        if (window.innerWidth <= 768) {
          /* Móvil: deslizar desde la izquierda */
          nav.classList.toggle('open');
        } else {
          /* Desktop: colapsar/expandir ancho */
          var collapsed = nav.classList.toggle('collapsed');
          if (content) {
            content.style.marginLeft = collapsed ? '0' : '';
          }
        }
      });

      /* Cerrar en móvil al hacer click fuera */
      document.addEventListener('click', function (e) {
        if (window.innerWidth <= 768 &&
            nav.classList.contains('open') &&
            !nav.contains(e.target) &&
            !toggle.contains(e.target)) {
          nav.classList.remove('open');
        }
      });
    }

    /* ── Active nav link ── */
    var links = document.querySelectorAll('.sb-sidenav .nav-link');
    var path  = window.location.pathname;
    links.forEach(function (link) {
      var href = link.getAttribute('href');
      if (href && href !== '/' && path.startsWith(href)) {
        link.classList.add('active');
      }
    });

    /* ── Estado badges en tablas ── */
    document.querySelectorAll('tbody td').forEach(function (td) {
      var val = (td.textContent || '').trim();
      if (val === 'PENDIENTE' || val === 'COMPLETADO') {
        td.innerHTML = '<span class="badge estado-' + val + '">' + val + '</span>';
      }
    });

    /* ── Auto-dismiss alerts después de 5s ── */
    document.querySelectorAll('.alert').forEach(function (alert) {
      setTimeout(function () {
        alert.style.transition = 'opacity .4s';
        alert.style.opacity    = '0';
        setTimeout(function () {
          if (alert.parentNode) alert.parentNode.removeChild(alert);
        }, 420);
      }, 5000);
    });
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
  } else {
    init();
  }
})();

/* ── Cliente Autocomplete ────────────────────── */
(function () {
  function initAutocomplete() {
    var input   = document.getElementById('clienteSearch');
    var list    = document.getElementById('clienteList');
    var hidden  = document.getElementById('clienteId');
    var clientes = window.MONACO_CLIENTES || [];

    if (!input || !list || !hidden) return;

    var activeIdx = -1;

    /* Resalta el texto que coincide */
    function highlight(text, query) {
      if (!query) return text;
      var escaped = query.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
      return text.replace(new RegExp('(' + escaped + ')', 'gi'),
        '<span class="ac-mark">$1</span>');
    }

    /* Muestra las sugerencias */
    function render(results, query) {
      list.innerHTML = '';
      activeIdx = -1;

      if (results.length === 0) {
        list.innerHTML = '<li class="autocomplete-empty">Sin resultados</li>';
        list.classList.add('show');
        return;
      }

      results.forEach(function (c, i) {
        var li = document.createElement('li');
        li.dataset.id = c.id_Cliente;
        li.dataset.idx = i;

        var nombre  = c.nombre  || '';
        var celular = c.celular || '';
        var dni     = c.dNI || c.DNI || c.dni || '';

        li.innerHTML =
          '<span class="ac-name">' + highlight(nombre, query) + '</span>' +
          '<span class="ac-sub">' +
            highlight(celular, query) +
            ' &nbsp;·&nbsp; DNI ' + highlight(dni, query) +
          '</span>';

        li.addEventListener('mousedown', function (e) {
          e.preventDefault(); // evita que el input pierda foco antes del click
          selectCliente(c, li.textContent.trim());
        });

        list.appendChild(li);
      });

      list.classList.add('show');
    }

    /* Selecciona un cliente */
    function selectCliente(c, displayText) {
      input.value  = c.nombre + ' — ' + c.celular;
      hidden.value = c.id_Cliente;
      list.classList.remove('show');
      input.classList.remove('is-invalid');
      activeIdx = -1;
    }

    /* Filtra los clientes */
    function search(query) {
      var q = query.trim().toLowerCase();
      if (q.length < 2) {
        list.classList.remove('show');
        return;
      }
      var results = clientes.filter(function (c) {
        return (c.celular || '').toLowerCase().startsWith(q) ||
               (c.nombre  || '').toLowerCase().includes(q)  ||
               (c.dNI || c.DNI || c.dni || '').toString().toLowerCase().startsWith(q);
      });
      render(results, q);
    }

    /* Eventos del input */
    input.addEventListener('input', function () {
      hidden.value = ''; // limpiar selección si escribe de nuevo
      search(this.value);
    });

    input.addEventListener('keydown', function (e) {
      var items = list.querySelectorAll('li[data-id]');
      if (!items.length) return;

      if (e.key === 'ArrowDown') {
        e.preventDefault();
        activeIdx = Math.min(activeIdx + 1, items.length - 1);
      } else if (e.key === 'ArrowUp') {
        e.preventDefault();
        activeIdx = Math.max(activeIdx - 1, 0);
      } else if (e.key === 'Enter' && activeIdx >= 0) {
        e.preventDefault();
        items[activeIdx].dispatchEvent(new Event('mousedown'));
        return;
      } else if (e.key === 'Escape') {
        list.classList.remove('show');
        return;
      }

      items.forEach(function (li, i) {
        li.classList.toggle('active', i === activeIdx);
        if (i === activeIdx) li.scrollIntoView({ block: 'nearest' });
      });
    });

    /* Cerrar al hacer click fuera */
    document.addEventListener('click', function (e) {
      if (!input.contains(e.target) && !list.contains(e.target)) {
        list.classList.remove('show');
        /* Si no hay ID seleccionado, limpiar el texto */
        if (!hidden.value) input.value = '';
      }
    });

    /* Validación: asegurarse de que haya un ID antes de enviar */
    var form = input.closest('form');
    if (form) {
      form.addEventListener('submit', function (e) {
        if (!hidden.value) {
          e.preventDefault();
          e.stopPropagation();
          input.classList.add('is-invalid');
          input.focus();
        }
      });
    }
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initAutocomplete);
  } else {
    initAutocomplete();
  }
})();
