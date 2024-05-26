-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: mediaflix4
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `product_id` bigint NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` bigint NOT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `page_number` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `FKka98u01ogtkot3g9ibmt3qfr4` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'James Dashner',9788493801311,'Nocturna',524),(3,'James Dashner',9788493920005,'Nocturna',496),(4,'James Dashner',9788493975036,'Nocturna',449),(5,'Alexandra Bracken',9788427203518,'Molino',544),(6,'Brandon Sanderson',9788417347291,'Nova',688),(7,'Brandon Sanderson',9788466658904,'Nova',784),(8,'Brandon Sanderson',9788418037818,'Nova',560),(9,'Stephen King',9788466357302,'DEBOLSILLO',1504),(10,'Stephen King',9788466357333,'DEBOLSILLO',488),(11,'Frank Herbert',9788466363402,'DEBOLSILLO',784),(12,'Frank Herbert',9788466372015,'DEBOLSILLO',304),(13,'Rebecca Yarros',9788408279990,'Planeta',736),(14,'Rebecca Yarros',9788408284550,'Planeta',896),(15,'Sarah J. Maas',9788408285298,'Crossbooks',456),(16,'Michael McDowell',9788419654892,'Blackie Books',272),(17,'Michael McDowell',9788419654915,'Blackie Books',272),(18,'Michael McDowell',9788419654939,'Blackie Books',272),(19,'Michael McDowell',9788419654953,'Blackie Books',272),(20,'Juan Gomez Jurado',9788466664417,'B',568),(21,'Joe HillJoe Hill',9788418440878,'NOCTURNA',408),(22,'Chugong',9788467945850,'NORMA',320),(23,'Hayime Isayama',9788467964141,'NORMA ',562),(24,'Robert C. Martin',9780132350884,'Pearson',464),(25,'Herbert Schildt',9788441539938,'ANAYA MULTIMEDIA',704),(26,'Suzanne Collins',9788413144856,'B',400),(27,'Suzanne Collins',9788413144863,'B',408),(28,'Suzanne Collins',9788413144870,'B',424),(29,'Suzanne Collins',9788413144887,'B',592),(30,'J. R. R. Tolkien',9788445013953,'Booket',576),(31,'J. R. R. Tolkien',9788445013960,'Booket',480),(32,'J. R. R. Tolkien',9788445013977,'Booket',608),(33,'J. R. R. Tolkien',9788445013946,'Booket',288),(34,'Dan Brown',9788408176022,'Planeta',624),(35,'Dan Brown',9788408099970,'Planeta',592);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `client_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `creation_date` date NOT NULL,
  `available` bit(1) NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `nif` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `hiring_date` date NOT NULL,
  `salary` decimal(38,2) NOT NULL,
  `available` bit(1) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `product_id` bigint NOT NULL,
  `developer` varchar(255) NOT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `FKt8dc4r00vrftr6py5qis3aeiw` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `product_id` bigint NOT NULL,
  `director` varchar(255) NOT NULL,
  `studio` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `FK50l10odqe7jn03a2y2osaxxbi` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `product_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(38,2) NOT NULL,
  PRIMARY KEY (`product_id`,`order_id`),
  KEY `FKinivj2k1370kw224lavkm3rqm` (`product_id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  CONSTRAINT `FKinivj2k1370kw224lavkm3rqm` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) NOT NULL,
  `total` decimal(38,2) NOT NULL,
  `status` enum('PENDIENTE','COMPLETADO','CANCELADO') DEFAULT NULL,
  `payment_method` varchar(255) NOT NULL,
  `client_id` bigint NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK17yo6gry2nuwg2erwhbaxqbs9` (`client_id`),
  KEY `FKog5v9ga2g2ukytypn4ocip6b4` (`employee_id`),
  CONSTRAINT `FK17yo6gry2nuwg2erwhbaxqbs9` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `FKog5v9ga2g2ukytypn4ocip6b4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `stock` int NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `rating` decimal(38,2) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `release_date` date NOT NULL,
  `url` varchar(255) NOT NULL,
  `product_type` enum('BOOK','MOVIE','GAME') NOT NULL,
  `available` tinyint NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'El corredor del laberinto','Bienvenido al bosque. Verás que una vez a la semana, siempre el mismo día y a la misma hora, nos llegan víveres. Una vez al mes, siempre el mismo día y a la misma hora, aparece un nuevo chico, como tú. Siempre un chico. Como ves, este lugar está cercado por muros de piedra… Has de saber que estos muros se abren por la mañana y se cierran por la noche, siempre a la hora exacta. Al otro lado se encuentra el laberinto. De noche, las puertas se cierran… y, si quieres sobrevivir, no debes estar allí para entonces',5,9.95,4.05,'Ciencia Ficcion','Español','2015-04-30','https://m.media-amazon.com/images/I/71zu3iDFScL._SY466_.jpg','BOOK',1),(3,'Las pruebas','Resolver el laberinto se suponía que era el final. No más pruebas, no más huidas. Thomas creía que salir significaba que todos recobrarían sus vidas, pero ninguno sabía a qué clase de vida estaban volviendo. Árida y carbonizada, gran parte de la tierra es un territorio inservible. El sol abrasa, los gobiernos han caído y una misteriosa enfermedad se ha ido apoderando poco a poco de la gente. Sus causas son desconocidas; su resultado, la locura. En un lugar infestado de miseria y ruina, y por donde la gente ha enloquecido y deambula en busca de víctimas, Thomas conoce a una chica, Brenda, que asegura haber contraído la enfermedad y estar a punto de sucumbir a sus efectos. Entretanto, Teresa ha desaparecido, la organización CRUEL les ha dejado un mensaje, un misterioso chico ha llegado y alguien ha tatuado unas palabras en los cuellos de los clarianos. La de Minho dice «el líder»; la de Thomas, «el que debe ser asesinado»',7,18.95,3.90,'Ciencia Ficcion','Español','2011-09-08','https://m.media-amazon.com/images/I/51uxtUiosIL._SY445_SX342_.jpg','BOOK',1),(4,'La cura mortal','«MÁTAME. SI ALGUNA VEZ HAS SIDO MI AMIGO, MÁTAME». Desde hace tres semanas, Thomas vive en una habitación sin ventanas, de un blanco resplandeciente y siempre iluminada. Sin reloj y sin contacto con nadie, más allá de las tres bandejas de comida que alguien le lleva a diario (aunque a horas distintas, como para desorientarle).',7,19.95,3.78,'Ciencia Ficcion','Español','2013-11-14','https://m.media-amazon.com/images/I/61t8XD7M2wL._SY466_.jpg','BOOK',1),(5,'Mentes poderosas','Cuando Ruby despierta en su décimo cumpleaños, algo en ella ha cambiado. Algo lo suficientemente alarmante como para que sus padres la encierren en el garaje y llamen inmediatamente a la policía buscando ayuda. Ha sucedido. Un fenómeno inexplicable le ha arrancado de la vida que siempre ha conocido, y la ha repudiado a Thurmond, el escalofriante campo de rehabilitación del gobierno donde se destinan a los supervivientes. Ruby no ha sucumbido a la misteriosa enfermedad que ha aniquilado a la mayoría de niños de Estados Unidos, pero ella y los demás prisioneros se han convertido en algo mucho peor: porque han desarrollado poderosas habilidades mentales que no pueden controlar.',7,18.95,4.16,'Ciencia Ficcion','Español','2011-09-08','https://m.media-amazon.com/images/I/81w3PhxdubL._SY466_.jpg','BOOK',1),(6,'Imperio Final','Durante mil años han caído las cenizas y nada florece. Durante mil años los skaa han sido esclavizados y viven sumidos en un miedo inevitable. Durante mil años el Lord Legislador reina con un poder absoluto gracias al terror, a sus poderes e inmortalidad. Le ayudan «obligadores» e «inquisidores», junto a la poderosa magia de la «alomancia». Pero los nobles, con frecuencia, han tenido trato sexual con jóvenes skaa y, aunque la ley lo prohíbe, algunos de sus bastardos han sobrevivido y heredado los poderes alománticos: son los «nacidos de la bruma» (mistborns). Ahora, Kelsier, el «superviviente», el único que ha logrado huir de los Pozos de Hathsin, ha encontrado a Vin, una pobre chica skaa con mucha suerte... Tal vez los dos unidos a la rebelión que los skaa intentan desde hace mil años puedan cambiar el mundo y la atroz dominación del Lord Legislador.',12,19.85,4.48,'Fantasia','Español','2018-04-19','https://m.media-amazon.com/images/I/911Ug7hsR4L._SY466_.jpg','BOOK',1),(7,'El pozo de la ascensión','Durante los ultimos mil años, han caido las cenizas y nada florece. Durante mil años, los skaa han sido esclavizados y han vivido sumidos en un miedo inevitable. Durante mil años, el Lord Legislador ha reinado con un poder absoluto gracias al terror y a su divina invencibilidad por la poderosa magia de la alomancia. Pero vencer y matar al Lord Legislador fue la parte sencilla. El verdadero desafio lo constituira sobrevivir a las consecuencias de su caida. Tomar el poder tal vez resulto facil, pero ¿que ocurre despues?, ¿como se usa el poder? Una amena reflexion sobre la estrategia politica y religiosa en el marco de una aventura epica con luchas estilo kung fu gracias a los siempre misteriosos poderes de la alomancia...',9,19.95,4.59,'Fantasía','Español','2016-09-21','https://m.media-amazon.com/images/I/511PjVMPctL._SY445_SX342_.jpg','BOOK',1),(8,'Trenza del mar Esmeralda: Una novela del Cosmere','En su isla natal sobre un océano verde esmeralda, la única vida que Trenza conoce es sencilla, marcada por el placer de coleccionar las tazas que traen los marineros de tierras lejanas y escuchar las historias que le cuenta su amigo Charlie. Pero cuando el padre de Charlie se lo lleva en barco para buscarle esposa y sucede una catástrofe, Trenza deberá colarse como polizona en un barco y partir en busca de la hechicera que habita en el mortífero mar de Medianoche. Sobre unos océanos de esporas repletos de piratas, ¿podrá Trenza abandonar su tranquila vida y crearse un lugar en un océano donde una sola gota puede significar la muerte instantánea?',6,25.55,4.44,'Fantasía','Español','2011-09-08','https://m.media-amazon.com/images/I/8193QJlUHzL._SY466_.jpg','BOOK',1),(9,'IT','¿Quien o que mutila y mata a los niños de un pequeño pueblo norteamericano? ¿Por que llega cíclicamente el horror a Derry en forma de un payaso  siniestro que va sembrando la destrucción a su paso?',5,14.20,4.24,'Terror','Español','2021-10-07','https://m.media-amazon.com/images/I/41PXR352I6L._SY445_SX342_.jpg','BOOK',1),(10,'Cementerio de animales','Louis lo había comprobado: el gato estaba muerto, y por eso lo había  enterrado. Aun así, incomprensiblemente, el gato había vuelto a casa. Church estaba allí otra vez, como Louis Creed temía y deseaba. Porque su  hijita Ellie le había encomendado que cuidara del gato, y Church había  muerto atropellado. Louis lo había comprobado: el gato estaba muerto,  incluso lo había enterrado más allá del cementerio de animales. Sin  embargo, Chruch había regresado, y sus ojos eran más crueles y perversos  que antes. Pero volvía a estar allí y Ellie no lo lamentaría. Louis Creed sí lo lamentaría. Porque más allá del cementerio de  animales, más allá de la valla de troncos que nadie se atrevía a  traspasar, más allá de los cuarenta y cinco escalones, el maligno poder  del antiguo cementerio indio le reclamaba con macabra avidez...',2,11.35,4.06,'Terror','Español','2021-10-07','https://m.media-amazon.com/images/I/51djW--faeL._SY445_SX342_.jpg','BOOK',1),(11,'Dune','En el desertico planeta Arrakis, el agua es el bien más preciado y llorar a los muertos, el símbolo de máxima prodigalidad. Pero algo hace de Arrakis una pieza estrategica para los intereses del Emperador, las Grandes Casas y la Cofradía, los tres grandes poderes de la galaxia. Arrakis es el único origen conocido de la melange, preciosa especia y uno de los bienes más codiciados del universo. Al duque Leto Atreides se le asigna el gobierno de este mundo inhóspito, habitado por los indómitos Fremen y monstruosos gusanos de arena de centenares de metros de longitud. Sin embargo, cuando la familia es traicionada, su hijo y heredero, Paul, emprenderá un viaje hacia un destino más grande del que jamás hubiese podido soñar.',13,19.90,4.27,'Ciencia Ficcion','Español','2023-11-16','https://m.media-amazon.com/images/I/91bNnC0hTFL._SY466_.jpg','BOOK',1),(12,'El mesías de Dune','El mesías de Dune es la segunda entrega de la excepcional saga de Frank Herbert «Dune», considerada la mejor serie de ciencia ficción de todos los tiempos. Arrakis, también llamado Dune: un mundo desierto en pos del sueño de convertirse en un paraíso, cuna de mil guerras que se han extendido por todo el universo y de un anhelo mesiánico que intenta alcanzar el sueño más antiguo de la humanidad... Paul Atreides: un personaje mítico, perturbado por la cercana presencia de una sombra dominante: su hermana Alia. Y frente a ellos, los grandes intereses económicos, políticos y religiosos que sacuden los espacios interestelares: la CHOAM, la Cofradía espacial, el Landsraad, la Bene Gesserit... Todo ello, y mucho más, conforma esta segunda entrega de «Dune»: un fresco impresionante y una obra cumbre de la imaginación.',6,19.90,3.89,'Ciencia Ficcion','Español','2024-03-19','https://www.amazon.es/mesías-Dune-crónicas-Best-Seller/dp/8466372016/ref=tmm_hrd_swatch_0?_encoding=UTF8&qid=&sr=','BOOK',1),(13,'Alas De Sangre','Violet Sorrengail creía que se uniría al Cuadrante de los Escribas para vivir una vida tranquila, sin embargo, por órdenes de su madre, debe unirse a los miles de candidatos que, en el Colegio de Guerra de Basgiath, luchan por formar parte de la élite de Navarre: el Cuadrante de los Jinetes de dragones. Cuando eres más pequeña y frágil que los demás tu vida corre peligro, porque los dragones no se vinculan con humanos débiles. Además, con más jinetes que dragones disponibles, muchos la matarían con tal de mejorar sus probabilidades de éxito; y hay otros, como el despiadado Xaden Riorson, el líder de ala más poderoso del Cuadrante de Jinetes, que la asesinarían simplemente por ser la hija de la comandante general. Para sobrevivir, necesitará aprovechar al máximo todo su ingenio. Mientras la guerra se torna más letal Violet sospecha que los líderes de Navarre esconden un terrible secreto...',3,21.75,4.60,'Fantasia','Español','2023-11-15','https://m.media-amazon.com/images/I/91OI4F8Fa7L._SY466_.jpg','BOOK',1),(14,'Alas De Hierro','Todos esperaban que Violet Sorrengail muriera durante su primer año en el Colegio de Guerra Basgiath, incluso ella misma. Pero la Trilla fue tan solo la primera de una serie de pruebas imposibles destinadas a deshacerse de los indignos y los desafortunados. Ahora comienza el verdadero entrenamiento, y Violet no sabe cómo logrará superarlo. No solo porque es brutal y agotador o porque está diseñado para llevar al límite el umbral del dolor de los jinetes, sino porque el nuevo vicecomandante está empeñado en demostrarle lo débil que es, a menos que traicione al hombre al que ama. La voluntad de sobrevivir no será suficiente este año, porque Violet conoce el secreto que se oculta entre los muros del colegio, y nada, ni siquiera el fuego de dragón, será suficiente para salvarlos.',14,22.70,4.35,'Fantasia','Español','2024-02-21','https://m.media-amazon.com/images/I/91BW1X31yIL._SY466_.jpg','BOOK',1),(15,'Una corte de rosas y espinas','Cuando la cazadora Feyre mata a un lobo en el bosque, una criatura bestial irrumpe en su casa para exigir una compensación. Así, es trasladada a una tierra mágica y engañosa de la que solo había oído hablar en las leyendas, donde Feyre descubre que su captor no es un animal sino Tamlin: una divinidad inmortal y letal que alguna vez reinó en su mundo. Mientras Feyre vive en su castillo, lo que siente por Tamlin muta de una hostilidad helada a una pasión ardiente y feroz, a pesar de todas las mentiras y advertencias a las que queda expuesta en ese mundo fantástico, bello y peligroso. Además, una vil sombra ancestral crece sobre la tierra de las hadas día a día, y Feyre debe encontrar la forma de detenerla... o condenar a Tamlin –y a su mundo– para siempre.',4,16.31,4.20,'Fantasia','Español','2024-02-21','https://m.media-amazon.com/images/I/71AqcFrx3JL._SY466_.jpg','BOOK',1),(16,'BLACKWATER I. La riada','Las gélidas y oscuras aguas del río Blackwater inundan Perdido, un pequeño pueblo al sur de Alabama. Allí, los Caskey, un gran clan de ricos terratenientes, intentan hacer frente a los daños causados por la riada. Liderados por Mary-Love, la incontestable matriarca, y Óscar, su obediente hijo, los Caskey trabajan por recomponerse y salvaguardar su fortuna. Pero no cuentan con la aparición de la misteriosa Elinor Dammert. Una joven hermosa pero parca en palabras con un único objetivo: acercarse a los Caskey cueste lo que cueste.',7,9.40,4.10,'Ficción','Español','2024-02-07','https://m.media-amazon.com/images/I/810rfCcCMBL._SY466_.jpg','BOOK',1),(17,'BLACKWATER II. El dique','Mientras Perdido se recupera de la inundación, propone la construcción de un dique que impida una nueva catástrofe. Sin embargo con las obras comenzarán las corrientes impredecibles y las desapariciones. Mientras tanto, en el clan Caskey, la matriarca Mary-Love ve cómo sus intereses chocan con los de Elinor, su misteriosa nuera. Las tensiones entre ambas amenazan con destruir el pueblo entero. En Perdido se avecinan grandes cambios, y las consecuencias serán devastadoras.',5,9.40,4.25,'Ficción','Español','2024-02-21','https://m.media-amazon.com/images/I/71WrrNjec6L._SY466_.jpg','BOOK',1),(18,'BLACKWATER III. La casa','Perdido, 1928. El clan Caskey se desmorona con la cruenta guerra personal entre Mary-Love y Elinor. En los recovecos del caserón donde viven Elinor y Oscar se esconden crisis conyugales y existenciales con repercusiones que desafían la imaginación, mientras los peores recuerdos, aquellos que uno se esfuerza por mantener ocultos, acechan para tejer sus mortíferas redes y salir a flote.',7,9.40,4.50,'Ficción','Español','2024-03-06','https://m.media-amazon.com/images/I/71n5drQEYrL._SY466_.jpg','BOOK',1),(19,'BLACKWATER IV. La guerra','Comienza una nueva era para el clan Caskey: la persistencia y el trabajo duro de Elinor en Perdido por fin parecen dar sus frutos. Su control arraiga en los hogares de un pueblo que en el pasado desconfió de sus intenciones. Sus enemigos, poderosos antaño, decrecen en número y pierden fuerza. El conflicto armado en Europa trae sangre nueva a Perdido. En las tierras de los Caskey, los hombres van y vienen como marionetas. No saben que sus vidas penden de un hilo.',7,9.40,4.50,'Ficción','Español','2024-03-20','https://m.media-amazon.com/images/I/81TfIiNVtDL._SY466_.jpg','BOOK',1),(20,'Reina roja','Antonia Scott es especial. Muy especial. No es policía ni criminalista. Nunca ha empuñado un arma ni llevado una placa, y, sin embargo, ha resuelto decenas de crímenes. Pero hace un tiempo que Antonia no sale de su ático de Lavapiés. Las cosas que ha perdido le importan mucho más que las que esperan ahí fuera. Tampoco recibe visitas. Por eso no le gusta nada, nada, cuando escucha unos pasos desconocidos subiendo las escaleras hasta el último piso. Sea quien sea, Antonia está segura de que viene a buscarla. Y eso le gusta aún menos.',8,21.75,4.10,'Thriller, Misterio, Suspense','Español','2018-11-08','https://m.media-amazon.com/images/I/71GC9IBeQjL._SY466_.jpg','BOOK',1),(21,'El teléfono negro','Un sádico asesino secuestra a un adolescente y lo encierra en un sótano, donde un teléfono roto y sin conexión suena de vez en cuando con llamadas de los muertos. Una familia visita un extraño museo donde se exhibe el último aliento de quienes ya nos han dejado. Arthur Rod es un imán para los matones; no es fácil hacer amigos cuando eres el único chico hinchable de la ciudad. Los hijos de un cazador de vampiros caído en desgracia descubren un oscuro secreto. Francis Kay se despierta una mañana convertido en una monstruosa langosta. La relación entre dos hermanos cambia cuando uno descubre que es capaz de volar. En el cine Rosebud, una joven tan guapa como muerta conversa con algunos espectadores sobre las películas que más le gustan...',4,18.52,3.90,'Terror, Ficción','Español','2023-02-20','https://m.media-amazon.com/images/I/71u7kxqVv0L._SY466_.jpg','BOOK',1),(22,'Solo leveling 1','Jinwoo es un cazador de rango E, considerado como elivel más bajo dentro de la Asociación de cazadores, por lo quee le conoce como ÓEl cazador más debil. Día tras día pone eneligro su vida y se embarca en misiones del rango más bajoara poder sufragar los gastos de su madre enferma; sin embargo,n una de esas misiones, lo que en un principio parecían raid de rango D termina por ser una mazmorra de un nivelnusitado y las cosas empiezan a torcerse, ¿Lograrán Sung yus compañeros salir con vida? ¡Una historia llena de acción yonstruos al más puro estilo RPG!',7,14.95,4.48,'Fantasia, Comic, Manga, Novela Gráfica','Español','2021-05-28','https://m.media-amazon.com/images/I/81BtQT37wvS._SY466_.jpg','BOOK',1),(23,'Ataque a los titanes Integral 1','En un mundo dominado por el miedo a los titanes la raza humana, convertida en mero alimento para esos seres, se protege de la invasión confinándose tras altísimos muros y aislándose del mundo exterior. Sin embargo, esa paz aparente se rompe con la aparición de un titán que, tras destruir el muro, inicia una nueva contienda que decidirá, de una vez por todas, quien se hará con la hegemonía en el mundo.',11,24.50,4.47,'Fantasia, Comic, Manga, Novela Gráfica','Español','2023-11-22','https://m.media-amazon.com/images/I/4199lDPlp-L._SY445_SX342_.jpg','BOOK',1),(24,'Clean Code: A Handbook of Agile Software Craftsmanship','Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn’t have to be that way.',7,40.55,4.38,'Programación y desarrollo software','Inglés','2020-02-17','https://m.media-amazon.com/images/I/51E2055ZGUL._SY466_.jpg','BOOK',1),(25,'Java 9','El diseño de Java, su robustez, el respaldo de la industria y su fácil portabilidad han hecho de Java uno de los lenguajes con un mayor crecimiento y amplitud de uso en distintas áreas de la industria de la informática. Su gran impacto en el desarrollo web, su protagonismo en el ámbito de las aplicaciones para dispositivos móviles, e incluso su sencillez y dinamismo para crear aplicaciones de escritorio, hacen de Java la plataforma de desarrollo número uno del mundo. Herbert Schildt le presenta esta obra, totalmente actualizada para la plataforma Java Standard Edition 9 (Java SE 9), a través de un enfoque paso a paso repleto de ejemplos, evaluaciones y proyectos. Comienza con los aspectos básicos, como la forma de compilar y ejecutar un programa Java, para después analizar la sintaxis y las construcciones que conforman el núcleo del lenguaje Java. También se describen las funciones más avanzadas, como la programación de subprocesamiento múltiple, los genéricos, las expresiones lambda, Swing, JavaFX, y, naturalmente, la nueva e innovadora función modular de Java SE 9. Por último, incluye una introducción a JShell, la nueva herramienta de programación interactiva de Java. Al finalizar el libro, dispondrá de una sólida base para programar con Java.',7,72.15,4.43,'Programación y desarrollo software','Español','2011-09-08','https://m.media-amazon.com/images/I/61a3NJz5GYL._SL1293_.jpg','BOOK',1),(26,'Los Juegos del Hambre','En una oscura versión del futuro próximo, doce chicos y doce chicas se ven obligados a participar en un reality show llamado Los Juegos del Hambre. Solo hay una regla: matar o morir. Cuando Katniss Everdeen, una joven de dieciséis años se presenta voluntaria para ocupar el lugar de su hermana en los juegos, lo entiende como una condena a muerte. Sin embargo, Katniss ya ha visto la muerte de cerca y la supervivencia forma parte de su naturaleza.',3,12.95,4.34,'Ciencia Ficción, Novelas juveniles, Acción, Aventura','Español','2008-09-14','https://m.media-amazon.com/images/I/71sWTX++64L._SY466_.jpg','BOOK',1),(27,'Los Juegos del Hambre: En llamas','Katniss Everdeen ha sobrevivido a Los juegos del hambre. Pero el Capitolio quiere venganza. Contra todo pronóstico, Katniss Everdeen y Peeta Mellark siguen vivos. Aunque Katniss debería sentirse aliviada, se rumorea que existe una rebelión contra el Capitolio, una rebelión que puede que Katniss y Peeta hayan ayudado a inspirar. La nación les observa y hay mucho en juego. Un movimiento en falso y las consecuencias serán inimaginables.',2,12.95,4.33,'Ciencia Ficción, Novelas juveniles, Acción, Aventura','Español','2008-09-01','https://m.media-amazon.com/images/I/71x-u0e9GfL._SY466_.jpg','BOOK',1),(28,'Los Juegos del Hambre: Sinsajo','Katniss Everdeen ha sobrevivido dos veces a los Juegos del Hambre, pero no está a salvo. La revolución se extiende y, al parecer, todos han tenido algo que ver en el meticuloso plan, todos excepto Katniss. Aun así su papel en la batalla final es el más importante de todos. Katniss debe convertirse en el Sinsajo, en el símbolo de la rebelión... a cualquier precio.',6,12.95,4.09,'Novelas juveniles, Acción, Aventura, Ciencia Ficción, Suspenso','Español','2010-08-24','https://m.media-amazon.com/images/I/71TM4vbY9JL._SY466_.jpg','BOOK',1),(29,'Los Juegos del Hambre: Balada de pájaros cantores y serpientes','Es la mañana de la cosecha que dará comienzo a los décimos Juegos del Hambre. En el Capitolio, Coriolanus Snow, de dieciocho años, se prepara para una oportunidad única: alcanzar la gloria como mentor de los Juegos. La casa de los Snow, antes tan influyente, atraviesa tiempos difíciles, y su destino depende de que Coriolanus consiga superar a sus compañeros en ingenio, estrategia y encanto como mentor del tributo que le sea adjudicado. Todo está en su contra...',16,12.95,3.95,'Ciencia Ficción, Novela bélica, Acción, Aventura','Español','2020-05-19','https://m.media-amazon.com/images/I/71yy+FF3noL._SY466_.jpg','BOOK',1),(30,'El Señor de los Anillos 1. La Comunidad del Anillo','En la adormecida e idílica Comarca, un joven hobbit recibe un encargo: custodiar el Anillo Único y emprender el viaje para su destrucción en la Grieta del Destino. Acompañado por magos, hombres, elfos y enanos, atravesará la Tierra Media y se internará en las sombras de Mordor, perseguido siempre por las huestes de Sauron, el Señor Oscuro, dispuesto a recuperar su creación para establecer el dominio definitivo del Mal.',7,10.40,4.39,'Literatura fantástica, Alta fantasía, Aventuras, Libro de caballerías, Fantasía heroica','Español','2022-08-31','https://m.media-amazon.com/images/I/81DjOU3MIvL._SY466_.jpg','BOOK',1),(31,'El Señor de los Anillos II. Las Dos Torres','La Compañía se ha disuelto y sus integrantes emprenden caminos separados. Frodo y Sam continúan solos su viaje a lo largo del río Anduin, perseguidos por la sombra misteriosa de un ser extraño que también ambiciona la posesión del Anillo. Mientras, hombres, elfos y enanos se preparan para la batalla final contra las fuerzas del Señor del Mal.',4,10.40,4.48,'Literatura fantástica, Alta fantasía, Aventuras, Libro de caballerías, Fantasía heroica','Español','2022-08-31','https://m.media-amazon.com/images/I/81pklPm-FCL._SY466_.jpg','BOOK',1),(32,'El Señor de los Anillos 3. El Retorno del Rey','Los ejércitos del Señor Oscuro van extendiendo cada vez más su maléfica sombra por la Tierra Media. Hombres, elfos y enanos unen sus fuerzas para presentar batalla a Sauron y sus huestes. Ajenos a estos preparativos, Frodo y Sam siguen adentrándose en el país de Mordor en su heroico viaje para destruir el Anillo de Poder en las Grietas del Destino.',8,10.40,4.56,'Literatura fantástica, Alta fantasía, Aventuras, Libro de caballerías, Fantasía heroica','Español','2022-08-31','https://m.media-amazon.com/images/I/81bpcs-qViL._SY466_.jpg','BOOK',1),(33,'El Hobbit','Smaug parecía profundamente dormido cuando Bilbo espió una vez más desde la entrada. ¡Pero fingía! ¡Estaba vigilando la entrada del túnel!... Sacado de su cómodo agujero-hobbit por Gandalf y una banda de enanos, Bilbo se encuentra de pronto en medio de una conspiración que pretende apoderarse del tesoro de Smaug el Magnífico, un enorme y muy peligroso dragón...',1,10.40,4.29,'Novela, Literatura fantástica, Alta fantasía, Literatura infantil, Épico','Español','2022-08-31','https://m.media-amazon.com/images/I/71pkc6LjsaL._SY466_.jpg','BOOK',1),(34,'El código Da Vinci','Robert Langdon recibe una llamada en mitad de la noche: el conservador del museo del Louvre ha sido asesinado en extrañas circunstancias y junto a su cadáver ha aparecido un desconcertante mensaje cifrado. Al profundizar en la investigación, Langdon, experto en simbología, descubre que las pistas conducen a las obras de Leonardo Da Vinci… y que están a la vista de todos, ocultas por el ingenio del pintor. Langdon une esfuerzos con la criptóloga francesa Sophie Neveu y descubre que el conservador del museo pertenecía al priorato de Sión, una sociedad que a lo largo de los siglos ha contado con miembros tan destacados como sir Isaac Newton, Botticelli, Victor Hugo o el propio Da Vinci, y que ha velado por mantener en secreto una sorprendente verdad histórica.',10,18.95,3.91,'Novela, Suspenso, Misterio, Ficción, Detectivesca, Policíaca, Conspirativa','Español','2017-08-29','https://m.media-amazon.com/images/I/91wn4iMQ-kL._SY466_.jpg','BOOK',1),(35,'Ángeles y demonios','Robert Langdon, experto en simbología, es convocado a un centro de investigación suizo para analizar un misterioso signo marcado a fuego en el pecho de un físico asesinado. Allí, Langdon descubre el resurgimiento de una antigua hermandad secreta: los illuminati, que han emergido de las sombras para llevar a cabo la fase final de una legendaria venganza contra su enemigo más odiado: la Iglesia católica. Los peores temores de Langdon se confirman cuando los illuminati anuncian que han escondido una bomba en el corazón de la Ciudad del Vaticano. Con la cuenta atrás en marcha, Langdon viaja a Roma para unir fuerzas con Vittoria Vetra, una bella y misteriosa científica. Los dos se embarcarán en una desesperada carrera contrarreloj por los rincones menos conocidos del Vaticano.',4,18.95,3.91,'Novela, Suspenso, Misterio, Ficción, Detectivesca, Policíaca, Conspirativa','Español','2011-06-21','https://m.media-amazon.com/images/I/910G53ftyLL._SY466_.jpg','BOOK',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-26 13:52:20
