/* ================================================
   MONACO — datatables-simple-demo.js
   Inicializa Simple DataTables en todas las tablas
   con id="datatablesSimple"
   ================================================ */

document.addEventListener('DOMContentLoaded', function () {

  var tables = document.querySelectorAll('table#datatablesSimple');

  tables.forEach(function (table) {

    /* Detectar si la tabla es de pedidos (tiene columna Fecha de Entrega) */
    var headers = table.querySelectorAll('thead th');
    var fechaColIdx = -1;
    headers.forEach(function (th, i) {
      if (th.textContent.trim().toLowerCase().includes('fecha')) {
        fechaColIdx = i;
      }
    });

    var options = {
      searchable: true,
      sortable:   true,
      perPage:    15,
      perPageSelect: [10, 15, 25, 50],
      labels: {
        placeholder: 'Buscar…',
        perPage:     'por página',
        noRows:      'Sin resultados',
        info:        'Mostrando {start} a {end} de {rows} registros',
      },
    };

    /* Si hay columna de fecha: ordenar descendente al iniciar */
    if (fechaColIdx >= 0) {
      options.data = { headings: null };
      var dt = new simpleDatatables.DataTable(table, options);
      /* Ordenar por fecha descendente después de renderizar */
      dt.on('datatable.init', function () {
        dt.columns.sort(fechaColIdx, 'asc');
      });
    } else {
      new simpleDatatables.DataTable(table, options);
    }
  });

});
