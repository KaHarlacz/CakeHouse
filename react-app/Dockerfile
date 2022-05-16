FROM node:alpine

COPY . .

ENV NODE_OPTIONS=--openssl-legacy-provider
ENV REACT_APP_CAKEHOUSE_BACKEND_URL=localhost:8080

CMD ["npm","start"]