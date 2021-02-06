import { Balance } from "./models/balance"
import { MoneyUnit } from "./models/moneyunit"

export const fixDecimals = (number: number) => {
    return Math.round((number + Number.EPSILON) * 100) / 100
}

export const compareMoneyUnitByValueDesc = (balance1: MoneyUnit, balance2: MoneyUnit) => balance2.value - balance1.value

export const timeSince = (date:Date) => {

    var seconds = Math.floor((new Date().getTime() - date.getTime()) / 1000);
  
    var interval = seconds / 31536000;
  
    if (interval > 1) {
      return Math.floor(interval) + " años" ;
    }
    interval = seconds / 2592000;
    if (interval > 1) {
      return Math.floor(interval) + " meses";
    }
    interval = seconds / 86400;
    if (interval > 1) {
      return Math.floor(interval) + " días";
    }
    interval = seconds / 3600;
    if (interval > 1) {
      return Math.floor(interval) + " horas";
    }
    interval = seconds / 60;
    if (interval > 1) {
      return Math.floor(interval) + " minutos";
    }
    return Math.floor(seconds) + " segundos";
  }