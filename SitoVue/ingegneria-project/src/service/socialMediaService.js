export const socialMediaService = {
    getData() {
        return [
                "Facebook",
                "Twitter/X",
                "Instagram",
                "LinkedIn",
                "Snapchat",
                "YouTube",
            
        ];
    },

    getSocialMedia() {
        return Promise.resolve(this.getData());
    }
};