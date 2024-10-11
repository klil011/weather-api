# Weather API Application

**Disclaimer**: L'idea di questo progetto è stata acquisita dal sito [Developer RoadMaps](https://roadmap.sh). L'idea del progetto può essere trovata al seguente link: [Weather API Wrapper Service](https://roadmap.sh/projects/weather-api-wrapper-service).

Questa è un'applicazione Spring Boot che recupera dati meteorologici da un'API di terze parti [VisualCrossing](https://www.visualcrossing.com/) e memorizza i dati nella cache in memoria utilizzando [Redis](https://redis.io/). I dati vengono recuperati in base alla città fornita dall'utente e, se i dati sono già presenti nella cache, vengono prelevati da Redis senza effettuare una nuova chiamata API.

## Funzionalità

- Recupero dei dati meteorologici dall'API di VisualCrossing.
- Memorizzazione dei dati meteorologici in Redis per 12 ore per evitare chiamate API frequenti.
- Se i dati non sono disponibili in Redis, vengono recuperati automaticamente dall'API e salvati in Redis.

## Prerequisiti

Prima di eseguire l'applicazione, è necessario avere installato quanto segue:

1. **Java 11+**
2. **Maven 3.x+** (per la compilazione del progetto)
3. **Redis** (in esecuzione localmente)

## Per iniziare

### Configurare la chiave API di VisualCrossing

È necessario ottenere una chiave API da VisualCrossing per poter recuperare i dati meteorologici.

Una volta ottenuta la chiave API, aggiungerla alle proprietà dell'applicazione.

Aprire il file `src/main/resources/application.properties` e aggiungere la seguente riga:

    api.weather.key={visual_crossing_api_key}

**Configurazione di Redis**

Assicurarsi di avere un'istanza di Redis in esecuzione sulla propria macchina (io ho utilizzato un'istanza locale per semplicità, ma si può utilizzare anche un'istanza remota).

Se si utilizza un'istanza locale di Redis, non è necessario modificare alcuna configurazione. Di default, l'applicazione si aspetta che Redis sia in esecuzione su localhost:6379.

Se il server Redis è in esecuzione su un host o porta diversa, bisogna aggiornare il file application.properties con la configurazione appropriata:

    spring.redis.host={redis_host}
    spring.redis.port={redis_port}

**Compilazione ed esecuzione dell'applicazione**

Per compilare il progetto, eseguire:

    mvn clean install

Per avviare l'applicazione, eseguire:

    mvn spring-boot:run

L'applicazione verrà avviata all'indirizzo http://localhost:8080.

**Per testare l'applicazione**

È possibile testare l'endpoint dell'API meteo effettuando una richiesta GET al seguente URL (io ho utilizzato PostMan), sostituendo city_name con la città che si desidera interrogare:

    http://localhost:8080/api/weather/{city_name}

**Meccanismo di caching**

Quando viene effettuata una richiesta meteo per una città:

- L'applicazione verifica se i dati per quella città sono già presenti in Redis.
- Se i dati sono presenti in Redis, restituisce i dati memorizzati nella cache.
- Se i dati non sono presenti in Redis, l'applicazione effettua una richiesta all'API VisualCrossing, recupera i dati e li memorizza in Redis per 12 ore.

**Cosa ho imparato da questo progetto?**

- Come lavorare con API di terze parti.
- Come configurare e utilizzare Redis per il caching.
- Serializzazione e deserializzazione JSON.
- Come strutturare un'API.
- Come gestire le variabili d'ambiente.
