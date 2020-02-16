function confTabelaSimples(tabela, tituo, coluna) {
    $(document).ready(function () {
        $(tabela).DataTable({
            "aaSorting":[[0, "asc"]], // ordenação pela coluna
            'paging': true,
            'lengthChange': true,
            'searching': true,
            'info': true,
            'autoWidth': false,
            'dom': 'B<"top"l>frti<"top"p><"clear">',
            'buttons': [{
                'extend': 'excel',
                'text': '<i class="fa fa-file-excel-o"></i>',
                'className': 'btn btn-default'
            },
                {
                    'extend': 'print',
                    'text': '<i class="fa fa-print"></i>',
                    'className': 'btn btn-default'
                },
                {
                    'extend': 'csv',
                    'text': '<i class="fa fa-file-code-o"></i>',
                    'className': 'btn btn-default',
                    'exportOptions': {
                        'modifier': {
                            'search': 'none'
                        }
                    }
                }
            ],
            'iDisplayLength': 25,
            "scrollX": true,
            "language": {
                "sEmptyTable": "Nenhuma "+tituo+" encontrada",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ "+tituo+"",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 "+tituo+"",
                "sInfoFiltered": "(Filtrados de _MAX_ "+tituo+")",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhums "+tituo+" encontrada",
                "sSearch": "Pesquisar: ",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                search : "_INPUT_",
                searchPlaceholder : "buscar..."
            }
        });
    });
};