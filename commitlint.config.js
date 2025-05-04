module.exports = {
    extends: ['@commitlint/config-conventional'],
    rules: {
        'type-enum': [
            2,
            'always',
            [
                'build',     // mudanças que afetam o sistema de build ou dependências externas
                'chore',     // tarefas que não se encaixam em outras categorias
                'ci',        // mudanças em arquivos de configuração de CI/CD
                'docs',      // documentação apenas
                'feat',      // nova funcionalidade
                'fix',       // correção de bug
                'perf',      // melhorias de performance
                'refactor',  // mudanças no código que não corrigem bugs nem adicionam funcionalidades
                'revert',    // reverte um commit anterior
                'style',     // mudanças que não afetam a lógica (espaços, formatação)
                'test'       // adição ou modificação de testes
            ]
        ],
        'subject-empty': [2, 'never'],
        'subject-case': [0],
        'type-case': [2, 'lower-case'],
        'header-max-length': [2, 'always', 100]
    }
};
