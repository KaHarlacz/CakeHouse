FROM node:alpine
WORKDIR /react-app

COPY . .
RUN npm uninstall node-sass
RUN npm i sass
ENV NODE_OPTIONS=--openssl-legacy-provider
CMD ["npm","start"]