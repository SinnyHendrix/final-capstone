<template>
  <div class="footer-container">
    <div class="copyright">
      <p>
        Magic: The Gathering Slytherin Card Collector is unofficial Fan Content permitted under
        the Fan Content Policy. Not approved/endorsed by Wizards. Portions of
        the materials used are property of Wizards of the Coast. ©Wizards of the
        Coast LLC.
      </p>
      <p>Magic: The Gathering Slytherin Card Collector is powered by the Scryfall LLC API.</p>
    </div>
    <div class="team-info">
      <h2>Tech Elevator Team Slytherin</h2>
      <img id="team-logo" src="src/images/team_slytherin_pic.jpeg" />
    </div>

    <div class="team-members">
      <a target="_blank" href="https://www.linkedin.com/in/cdbradshaw/"
        >Cheryl Bradshaw</a
      >
      <br />
      <a target="_blank" href="https://www.linkedin.com/in/jonathan-sinclair/"
        >Jonathan Sinclair</a
      >
      <br />
      <a target="_blank" href="https://www.linkedin.com/in/israelryan/"
        >Israel Ryan</a
      >
      <br />
      <a target="_blank" href="https://www.linkedin.com/in/jeremey-scofield/"
        >Jeremey Scofield</a
      >
    </div>
  </div>
</template>

<script></script>

<style scoped>
.footer-container {
  border-top: solid rgb(167, 80, 53) 2px;
  margin: 10px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  row-gap: 20px;
  column-gap: 50px;
  align-items: center;
  grid-template-areas: "copyright team-info team-members";
}
.copyright {
  grid-area: copyright;
  font-size: 0.75em;
  font-style: italic;
  color: rgb(165, 164, 165);
  padding: 5px;
}

.team-info {
  grid-area: team-info;
  font-size: 1em;
  display: grid;
}
.team-info > h2 {
  margin-left: auto;
  margin-right: auto;
  display: block;
  text-align: center;
}

.team-members {
  grid-area: team-members;
  text-align: right;
  padding-right: 5px;
}

#team-logo {
  max-width: 30%;
  display: block;
  margin-left: auto;
  margin-right: auto;
}
</style>
