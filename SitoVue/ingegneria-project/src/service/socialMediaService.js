export const socialMediaService = {
    getData() {
        return [
            'facebook.com',
            'twitter.com',
            'instagram.com',
            'linkedin.com',
            'snapchat.com',
            'youtube.com',
            'pinterest.com',
            'reddit.com',
            'tiktok.com',
            'tumblr.com',
            'whatsapp.com',
            'wechat.com',
            'telegram.org',
            'discord.com',
            'quora.com',
            'vimeo.com',
        ];
    },

    getSocialMedia() {
        return Promise.resolve(this.getData());
    },
};
