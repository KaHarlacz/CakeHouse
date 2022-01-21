function base64ToImage(b64String) {
  const image = new Image();
  image.src = 'data:image/png;base64,' + b64String;
  return image;
}