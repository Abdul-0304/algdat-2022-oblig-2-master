# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Abdul Moiz Ehsan, S364554, s364554@oslomet.no
* Mohammad Junaid Ali, S364589, s364589@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å først tegne oppgaven, deretter så fant vi inspirasjon fra kompendiet og gjorde tom og antall metoden. Også
lagde vi kontruktøren, vi gikk frem ved å først tegne ned alle kravene deretter satt vi oss inn i koden. Vi brukte while-løkker, og satt hode til første
verdien som ikke er null. Deretter gikk vi gjennom resterende verdiene. Vi måtte tegne ned hva som egentlig skjer, før vi begynte å kode det.

I oppgave 2 så brukte vi lang tid på å løse, vi tok mye inspirasjon fra komdendiet og kodet det selv. Oppgave 2a, det å lage tostring og omvendtstring
metoden, så var det egentlig nesten det samme. Bare at på tostring brukte vi hode, men på omvendtstring brukte vi hale. Deretter 2b, den var ganske lett
med tanke vi tok inspirasjon fra komdendiet. Metoden legger til verdien bakerst i listen, hvis listen er tom
da vi hode og hale bli satt som null.

Oppgave 5
Fullfører void legginn metoden. Startet må sette av en object.requireNoNull. Den gjør at vi ikke tar inn noen null verdier og
skriver ut en feilmeldings tekst. Deretter bruker vi indekskontroll metoden som skjekker om indeks = antall er lovelig,
Det gir ut true. if setingen gjlr at vi setter den nye verdien først. Deretter så skjekkes det om listen er tom og hvis den er det
så skal hode og hale sine neste og forgie settes til null. Deretter kommer else setningen som da setter den nye verdien til å være en ny node som er hode.
For å sette verdien til hode setter vi også hode sin forggie peker til null.
Vi lager også en else-if setning som sier hvis indeksen til verdein er lik antall er vi i siste posisjon og den nyverdien
settes til siste posisjon. Dette gjøre på en lik måte som else setningen tidligere. ved at vi setter den nye verdien til å bli en ny node hale 
og vi setter hale sin neste til null. Det gjør at verdien vil bli satt i siste posisjon som den nye halen. 

Oppgave 6
Fullførte boolean fjern metoden ved å lage en if-setning som skjekker om verdien vi har satt inn inneholder i listen.
Dette gjør vi ved å kalle på metoden inneholder og sette inn verdien fra parameteret. Hvis if-setningen stemmer
da går vi inn i if setningen og fjerner den verdien ved bruk av den andre fjern metoden (T fjern(int indeks)).
Deretter returnerer vi true siden verdien ble fjernet. Om vi verdien vår ikke finnes i listen så skjer ingenting fordi
else setningen er tom og da returnerer vi false.

Oppgave 7
Her lages nullstill metoden. Nullstillingen skjer ved å først sette hode og hale til null.
antallet vårt blir satt som 0. og endringene våre økes med 1. Dette var krav fra oppgaven.
Deretter opprettes en generisk node som vi kaller for current som blir satt som hode. Deretter lages en while løkke
som skjekker om current ikke er null. Hver gang current har en verdi og ikke er tom så, settes currenten sin verdi til null.
ved hjelp av neste og forgie pekere setter vi den neste noden og forgie noden til null. til slutt tar vi en current sin neste
for å hoppe videre til neste node. Siden vi tidligere satte current sin neste til null, så vil while løkken sitt argument altid
stemme og hver node i listen vil bli satt til null. Til slutt har vi en else setning for else-if argumentet vårt. 
Denne else setningen oppretter to noder. En node m og en node j. Node m gjør at m flyttes indeks - 1 ganger.
Node j oppdaterer pekerene sine til å få m sine pekere.
