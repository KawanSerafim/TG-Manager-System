<div>
    <h1 align="center">Convenções Dev-Back-End</h1>
</div>

<div align="center">
    <img width="100%" alt="img" src="https://burst.shopifycdn.com/photos/developer-coding-in-php.jpg?width=1000&format=pjpg&exif=0&iptc=0"/>
</div>

## Versionamento do projeto

O versionamento deve seguir um padrão para que tudo esteja organizado e evite problemas de merge e outras dores de cabeça. Antes de tudo, é estritamente necessário que você tenha um perfil Git configurado. Caso não tenha, siga esses passos:

```bash
#Para configurar user-name
git config --global user.name "seu nome"

#Para configurar user-email
git config --global user.email "seu.email@exemplo.com"
```

Tendo o perfil Git configurado, a primeira regra é não atuar diretamente na branch main (master). Teremos duas branchs: 

- Uma que ramifica da main, e se chamará `develop`
- Uma que ramifica da develop, e se chamará `feature/sua_feature`

Na segunda branch é onde você irá trabalhar sua feature, e outros devs participando do projeto também irão, mas eles terão as próprias features. O nome da feature deve ser auto descritiva, e que mencione que funcionalidade você está trabalhando. Por exempo: `feature/fazer-login`

### Exemplo de Fluxo de Trabalho

1. Para uma nova feature, crie uma branch para ela:

```bash
# 1. Vá para a branch develop
git checkout develop

# 2. Garanta que ela está atualizada com o repositório remoto
git pull origin develop

# 3. Crie sua nova branch de feature a partir da develop
git checkout -b feature/fazer-login
```
2. Faça o seu trabalho nessa branch e commite suas alterações (de acordo com o padrão lá embaixo).

3. Para finalizar a feature:

```bash
# Envie a branch para o repositório remoto (GitHub)
git push origin feature/fazer-login
```
4. Para adicionar a nova feature na branch `main`, basta fazer um Pull Request no GitHub.

## Padrões de Commit

`feat`: Para uma nova funcionalidade (feature).

```bash
git commit -m"feat: fazer-login"
```

`fix`: Para a correção de um bug.

```bash
git commit -m"fix: bug corrigido na feature fazer-login"
```

`docs`: Para mudanças apenas na documentação (README, comentários, etc.).

```bash
git commit -m"docs: comentarios adicionados na classe FazerLoginCase.java"
```

`refactor`: Para refatoração de código que não corrige um bug nem adiciona uma feature.

```bash
git commit -m"refactor: laco For trocado por While na classe FazerLoginService.java"
```

`test`: Para adicionar ou corrigir testes.

```bash
git commit -m"test: teste unitario da feature fazer-login finalizado"
```

`chore`: Para tarefas de manutenção que não se encaixam nos outros tipos (atualizar dependências, configurar o build, etc.).

```bash
git commit -m"chore: dependência PDFBox do Apache adicionado ao projeto"
```