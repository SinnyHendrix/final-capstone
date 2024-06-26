<template>
  <div id="collectionsContainer">
    <div class="publicCollections" v-bind:to="{ name: 'view-collections' }">

      <h2 id="publicTitle">Slytherin Card Collections:</h2>

      <button id="getAllCollectionsButton" @click="getAllCollections"> get all collections </button>

      <p v-if="allCollections.length > 0">
        All Collections:
      <ul>
        <button id="collectionButtons" v-for="collection in allCollections" :key="collection.collectionId"
          class="allCollectionButton" @click="handleAllCollections(collection.collectionId)">
          {{ collection.collectionName }}
        </button>
      </ul>
      </p>

      <div id="collectionResults" v-for="collection in collections" v-bind:key="collection.collectionId">


        <h2 v-on:click="getCardsByCollectionsId(collection.collectionId)">View the {{ collection.collectionName }}
          collection from {{ collection.username }}</h2>

        <div id="cardResults" v-for="collectionId in collection" v-bind:key="collectionId">
          <card-front v-for="cardFront in cardCollection" v-bind:cardFront="cardFront" v-bind:key="cardFront.name" />
          {{ collection.collectionId }}

        </div>
      </div>
    </div>
    <div v-show="$store.state.token != ''">
      <div class="userCollections" v-bind:to="{ name: 'userName' }">
        <h2>{{ this.$store.state.user.username }}'s Collections:</h2>

        <button id="getMyCollectionsButton" @click="getCollectionsByUserId">Get My Collections</button>

        <p v-if="newCollections.length > 0">
          Your Collections:
        <ul>
          <button id="myCollectionsButtons" v-for="collection in newCollections" :key="collection.collectionId" class="collection-button"
            @click="handleMyCollections(collection.collectionId)">
            {{ collection.collectionName }}
          </button>
        </ul>
        </p>
      </div>
    </div>
      <div v-if="selectedCollection">
        <h2>Cards in {{ selectedCollection.collectionId }}</h2>
        <div v-if="selectedCollection.cards && selectedCollection.cards.length > 0">
          <div class="cards">
            <div v-for="(card, index) in selectedCollection.cards" :key="card.id">
              <div class="displayCard">
                <div class="card-image">
                  <img :src="imageList[index]" alt="Card Image" onError="this.src='placeholder.png'">
                </div>
                <button v-if="onPlayersCollections" @click="removeCardFromCollection(card)"> remove card </button>
              </div>
            </div>
          </div>
        </div>
        <p v-else>This collection has no cards yet.</p>
      </div>
    </div>
</template>

<script>
import CollectionService from '../services/CollectionService';
import axios from 'axios';

export default {
  props: [],
  components: {},
  data() {
    return {
      allCollections: [],
      newCollections: [],
      selectedCollection: null,
      cardInformation: {},
      imageList: [],
      onPlayersCollections: false,
    };
  },
  created() {
    this.getCollectionsByUserId();
    this.getAllCollections();
  },
  methods: {
    getCollectionsByUserId() {
      axios.get(`http://localhost:9000/collections/user/${this.$store.state.user.id}`)
        .then(response => {
          this.newCollections = response.data;
        })
        .catch(error => {
          console.error("Error fetching collections:", error.message);
        });
    },
    getCollectionCards(collectionId) {
      axios.get(`http://localhost:9000/collections/${collectionId}/cards`)
        .then(response => {
          this.selectedCollection = {
            collectionId,
            collectionName: response.data.collectionName,
            // Assuming response.data is an array of card objects
            cards: response.data,
          };
          this.imageList = []

          response.data.forEach((card) => {

            this.getCardImageUrl(card).then((imageUrl) => {
              this.imageList.push(imageUrl);
            })
          })

        })
    }
    ,
    getCardFromCardId(cardId) {
      return axios.get(`https://api.scryfall.com/cards/${cardId}`)
        .then(response => response.data.image_uris.normal); // Assuming normal-sized image
    },
    getCardImageUrl(cardId) {
      return axios
        .get(`https://api.scryfall.com/cards/${cardId}`)
        .then((response) => response.data.image_uris.small) // Assuming normal-sized image
        .catch(() => {
          return 'placeholder.png'; // Return placeholder image on error
        });
    },
    getAllCollections() {
      axios.get(`http://localhost:9000/collections`).then((response) => {
        this.allCollections = response.data;
      })

      this.onPlayersCollections = false;
    },
    removeCardFromCollection(card) {
      axios.post('http://localhost:9000/collections/cards/delete', {
        collectionId: this.selectedCollection.collectionId,
        cardId: card
      })
        .then(() => {
          // After successful deletion, filter the card out locally
          this.selectedCollection.cards = this.selectedCollection.cards.filter(c => c.id !== card.id);
          this.handleMyCollections(this.selectedCollection.collectionId);
        })
        .catch(error => {
          console.error("Error removing card:", error.message);
        });
    },
    handleAllCollections(collectionId) {
      this.getCollectionCards(collectionId);
      this.onPlayersCollections = false;
    },

    handleMyCollections(collectionId) {
      this.getCollectionCards(collectionId)
      this.onPlayersCollections = true;
    }

  },
};
</script>

<style scoped>
#collectionButtons {
  background-color: rgb(0, 0, 0);
  color: white;
}

.displayCard {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  width: 120px;
  margin: 20px;
}
h2{
  font-size: 2em;
  color: rgb(54, 104, 175);
}
.cards {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
#getAllCollectionsButton {
  background-color: rgb(54, 104, 175);
  color: white;
}
#getMyCollectionsButton {
  background-color: rgb(54, 104, 175);
  color: white;
}
#myCollectionsButtons {
  background-color: rgb(0, 0, 0);
  color: white;
}
</style>