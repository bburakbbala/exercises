using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IRepositoryAsync<T> where T : class
    {
        Task<T> GetAsync(int id);

        Task<T> GetAsync(Guid id);

        Task<T> GetAsync(string id);

        Task<IEnumerable<T>> GetAllAsync(
            Expression<Func<T, bool>> filter = null,
            Func<IQueryable<T>, IOrderedQueryable<T>> orderBy = null,
            string includeProperties = null // for eager loading
            );

        Task<T> GetFirstOrDefaultAsync(
            Expression<Func<T, bool>> filter = null,
            string includeProperties = null
            );

        Task AddAsync(T entity);
        Task RemoveAsync(int id);
        Task RemoveAsync(Guid id);
        Task RemoveAsync(string id);
        Task RemoveAsync(T entity);
        Task RemoveRangeAsync(IEnumerable<T> entity);
    }
}
