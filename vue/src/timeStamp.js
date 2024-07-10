export function updateTimeStamp(date = new Date()) {
    const month = date.getMonth() +1;
    const day = date.getDate();
    return `${month} / ${day}`;
  }