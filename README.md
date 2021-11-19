# Robotory

Projeto Java8 utilizando o software de gerenciamento de projetos Maven
<br>

## Compilar aplicação
- Caso não tenha instalado o JDK8 instalado execute no terminal:
```
sudo apt install openjdk-8-jdk
```
- Caso não tenha o Maven instalado execute no terminal:
```
sudo apt install maven
```
- No diretório raiz do projeto execute:
```
mvn clean package install
```

## Executar aplicação
No diretorio raiz do projeto:
- Garanta que a aplicação tem permissão de excução executando o comando:
```
chmod +rwx target/Robotory-0.0.1-SNAPSHOT.jar
```
- Execute a aplicação com o comando:
```
java -jar target/Robotory-0.0.1-SNAPSHOT.jar
```
