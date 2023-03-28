import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'orderBy'
})
export class OrderByPipe implements PipeTransform {

  transform(values: any[], field: string = 'id', sortOrder: string = 'asc'): any[] {
    values.sort(
      (obj1: any, obj2: any) => {
        if (obj1[field] < obj2[field])
          return -1;
        if (obj1[field] > obj2[field])
          return 1;
        return 0;
      }
    );
    return sortOrder === 'asc' ? values : values.reverse();

  }


}
