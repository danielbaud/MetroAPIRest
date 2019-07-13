# iMetro

This server uses the Play framework and the Scala programming language, 
to receive the status sent by our iMetros. Status are stored in cassandra
iMetro.status table. iMetro endpoints use RDD methods such as .map, .reduceByKey
and .filter.

## Installation

**Before running the project, you need to install SBT.**

```bash
sudo brew install sbt
```
*or*
```bash
sudo apt-get install sbt
```

**You also need Java SDK 8.**

```bash
brew tap adoptopenjdk/openjdk
brew cask install adoptopenjdk8
```
*or*
```bash
sudo apt-get install openjdk-8-jre
```

**And finaly cassandra database.**

```bash
brew install cassandra
```
*or*
```bash
sudo apt-get install cassandra
```

## Usage

```bash
cqlsh -f other/init.cql && cd src && sbt run
```

And go to http://localhost:9000 in your favorite web browser.

## License

This project is free of rights for no commercial use.
