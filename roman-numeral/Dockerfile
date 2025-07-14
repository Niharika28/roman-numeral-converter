# roman-numeral/Dockerfile
FROM node:18 as build

WORKDIR /roman-numeral

COPY . .

RUN npm install
RUN npm run build

FROM nginx:alpine

COPY --from=build /roman-numeral/build /usr/share/nginx/html
EXPOSE 80