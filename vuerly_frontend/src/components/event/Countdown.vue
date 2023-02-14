<template>
    <div>
        <p class="countdown">{{ days }}일 {{ formattedHours }}:{{ formattedMinutes }}:{{ formattedSeconds }}</p>
    </div>
</template>
  
<script>
export default {
    props: ['end'],
    data() {
        return {
            timeLeft: 0
        };
    },
    computed: {
        days() {
            return Math.floor(this.timeLeft / (1000 * 60 * 60 * 24));
        },
        hours() {
            return Math.floor((this.timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        },
        minutes() {
            return Math.floor((this.timeLeft % (1000 * 60 * 60)) / (1000 * 60));
        },
        seconds() {
            return Math.floor((this.timeLeft % (1000 * 60)) / 1000);
        },
        formattedHours() {
            return this.hours.toString().padStart(2, '0');
        },
        formattedMinutes() {
            return this.minutes.toString().padStart(2, '0');
        },
        formattedSeconds() {
            return this.seconds.toString().padStart(2, '0');
        }
    },
    mounted() {
        let currentDate = new Date();
        let endDate = new Date(this.end);
        this.timeLeft = endDate - currentDate;
        this.intervalId = setInterval(() => {
        this.timeLeft -= 1000;
        }, 1000);
    },
    beforeDestroy() {
        clearInterval(this.intervalId);
    }
};
</script>

<style scoped>
.countdown {
    margin: 0 !important;
    text-align: center;
}
</style>